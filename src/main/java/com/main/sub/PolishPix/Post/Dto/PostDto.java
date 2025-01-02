package com.main.sub.PolishPix.Post.Dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.main.sub.PolishPix.Post.Entity.Post;

import lombok.Data;

@Data
public class PostDto {
	private String post_content;
	private String insert_date;
	private String user_id;
    private String user_name;
    private String user_profile;
	private List<ImageDto> images;
	
    @Data
    public static class ImageDto {
        private String image_id;
        private String image_url;
    }
	
	public Post toEntity() {
		//post 설정 
		Post post = new Post();
		post.setPost_content(post_content);
		post.setInsert_date(insert_date);
		
		//user 설정 
		Post.User user = new Post.User();
        user.setUser_id(user_id);
        user.setUser_name(user_name);
        user.setUser_profile(user_profile);
        post.setUser(user);

        // Images 설정
        if (images != null) { //데이터가 잇을 경우에만 
        	
        	// stream : 데이터 소스를 추상화하고 데이터 소스에 상관없이 모두 같은 방식으로 다룰 수 있음        
        	
            List<Post.Image> imageEntities = images.stream() // images 리스트를 스트림으로 변환
                    .map(imageDto -> {  // 각 imageDto를 Post.Image 객체로 변환
                        Post.Image image = new Post.Image();
                        image.setImage_id(imageDto.getImage_id()); // imageDto에서 id 가져오기 
                        image.setImage_url(imageDto.getImage_url()); // url 갖고오기 
                        return image; 
                    })
                    .toList(); // 스트림에서 변환된 요소들을 List로 
            post.setImages(imageEntities); // 변환된 이미지 리스트를 post 객체의 images 필드에 설정 
        }
        
		return post;
	}
}