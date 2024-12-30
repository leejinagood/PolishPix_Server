package com.main.sub.PolishPix.Login.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.main.sub.PolishPix.Login.Entity.Login;
import com.main.sub.PolishPix.Login.Repository.LoginRepository;
import com.main.sub.PolishPix.Login.Dto.LoginDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    
}
