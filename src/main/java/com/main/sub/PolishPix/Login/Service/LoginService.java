package com.main.sub.PolishPix.Login.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.main.sub.PolishPix.Login.Entity.Login;
import com.main.sub.PolishPix.Login.Repository.LoginRepository;
import com.main.sub.PolishPix.Login.Dto.LoginDto;

import java.util.List;
import java.util.Optional;

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
    
    
    // 로그인 로직
    public String login(String email, String password) {
    	String message ="";
    	
    	Optional<Login> findUser = loginRepository.findByEmail(email); //이메일 존재 여부 확인 
    	
    	//Optional[Login(_id=6775f6c244ccfc44be73d158, email=testtest@naver.com, password=$2a$10$U6SChrmeYUuL8coqJtSWu.9Jy5SRk2d659VOxVfQ5fc0UWlGQXC4q, phone=010-1111-2222, name=테스트, profile=null)]
    	
    	if(findUser.isPresent() == false){ //isPresent함수는 존재여부에 대해.. 
    		message = "이메일이 존재하지 않습니다.";
    	}else {
    		String DBpassword = findUser.get().getPassword(); //DB에 저장되어 있는 password 값 가져오기 
    		boolean isMatch = passwordEncoder.matches(password, DBpassword); //입력한 비밀번호와 매치하는지 
    		
    		if(isMatch == true) { //비밀번호 일치 시 jwt 토큰 생성 후 리턴 
    			String token="";
    		}else if(isMatch == false) {
    			message = "비밀번호가 다릅니다.";
    		}
    	}
    	return message;
    }

    
}
