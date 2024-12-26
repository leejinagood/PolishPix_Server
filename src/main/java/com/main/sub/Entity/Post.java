package com.main.sub.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "post")
public class Post {
	
	@Id
	private String _id;
	private String post_content;
	private String insert_date;
	private User user; // 객체의 user_id만 따로 
    private List<Image> images; //images 배열 
    
    @Data
    public static class User {
    	private String user_id;
        private String user_name;
        private String user_profile;
    }

    @Data
    public static class Image {
        private String image_id;
        private String image_url;
    }
}