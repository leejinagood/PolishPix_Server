package com.main.sub.PolishPix.Like.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "likes")
public class Like {
	
	@Id
	private String _id;
	private String post_id;
	private String user_id;
	
}


