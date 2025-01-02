package com.main.sub.PolishPix.Login.Service;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.sub.PolishPix.Login.Entity.Login;
import com.main.sub.PolishPix.Login.Repository.LoginRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.main.sub.PolishPix.Login.Dto.LoginDto;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    // 회원가입 로직
    public Login signup(LoginDto dto) {
        // DTO를 엔티티로 변환
        Login loginEntity = dto.toEntity();

        // 이메일 형식 유효성 검사
        String email = loginEntity.getEmail();
        if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("유효한 이메일 형식이 아닙니다.");
        }

        // 비밀번호 유효성 검사 (예: 최소 길이 8자, 숫자 포함, 특수 문자 포함 등)
        String password = loginEntity.getPassword();
        if (password == null || password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*()].*")) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상, 숫자와 특수 문자를 포함해야 합니다.");
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(loginEntity.getPassword());
        loginEntity.setPassword(encryptedPassword);  // 암호화된 비밀번호를 엔티티에 설정

        
        // 이메일 중복 체크
        if (loginRepository.findByEmail(loginEntity.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // DB에 저장
        return loginRepository.insert(loginEntity);
    }
    
    
    //application.propertise에서 관리하는 인증키 
    @Value("${jwt.secretKey}")
    private String secretKeyString;
    
    
    // 로그인 로직
    public String login(String email, String password) {

        Optional<Login> findUser = loginRepository.findByEmail(email); //받은 이메일을 가지고 db에서 일치하는 데이터 가져오기 

        if (!findUser.isPresent()) {
            return "이메일이 존재하지 않습니다.";
        }

        String DBpassword = findUser.get().getPassword(); //입력한 비번이랑 db에 저장 되어있는 비번이랑 맞는지 확인 
        boolean isMatch = passwordEncoder.matches(password, DBpassword);

        
        if (isMatch) { // 비밀번호까지 다 맞았을 경우 Jwt 토큰 생성
        	
        	byte[] secretKeyBytes = java.util.Base64.getDecoder().decode(secretKeyString);
            SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);  // HMAC-SHA256을 위한 SecretKey

            String token = Jwts.builder()
                    .claim("email", findUser.get().getEmail()) // 이메일 claim에 저장
                    .claim("name", findUser.get().getName())  // 이름 claim에 저장
                    .claim("role", "USER")  // 사용자 권한 설정
                    .setIssuedAt(new Date())  // 발행 시간 설정
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))  // 30분 유효 기간
                    .signWith(secretKey)  // 서명 설정
                    .compact();

            return token; // 생성된 JWT 토큰 반환
        } else {
            return "비밀번호가 다릅니다.";  // 비밀번호 불일치 처리
        }
    }

    
    
}
