package com.main.sub.PolishPix.User.Service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.main.sub.PolishPix.User.Repository.UserRepository;
import com.main.sub.PolishPix.User.Entity.User;
import com.main.sub.PolishPix.User.Dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    
    public  User getId(String id){
    	return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User id " + id + " 존재하지 않음 "));

    }

    public User update(UserDto userDto) {
        // 이메일로 기존 사용자 조회
        User existingUser = this.getEmail(userDto.getEmail());

        // 기존 사용자 정보를 업데이트
        existingUser.setEmail(userDto.getEmail());
        existingUser.setName(userDto.getName());
        existingUser.setPhone(userDto.getPhone());
        existingUser.setProfile(userDto.getProfile());

        // 변경된 사용자 정보를 저장
        return this.userRepository.save(existingUser);
    }
    
}
    
    
    
    
    
    