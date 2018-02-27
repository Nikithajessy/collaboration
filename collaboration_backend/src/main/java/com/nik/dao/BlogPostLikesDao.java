package com.nik.dao;

import com.nik.model.BlogPost;
import com.nik.model.BlogPostLikes;

public interface BlogPostLikesDao {

	BlogPostLikes hasUserLikedPost(int postId,String email);
	BlogPost updateLikes(int postId,String email);
}
