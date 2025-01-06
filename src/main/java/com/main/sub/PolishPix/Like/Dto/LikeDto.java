package com.main.sub.PolishPix.Like.Dto;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;
import com.main.sub.PolishPix.Like.Entity.Like;
import lombok.Data;

@Data
public class LikeDto {
	private String user_id;
	private String post_id;
	
	public Like toEntity() {
		//like 설정 
		Like like = new Like();
		like.setUser_id(user_id);
		like.setPost_id(post_id);
		
        
		return like;
	}
}