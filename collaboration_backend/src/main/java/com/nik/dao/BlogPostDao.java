package com.nik.dao;

import java.util.List;

import com.nik.model.BlogPost;

public interface BlogPostDao {
	
	void addBlogPost(BlogPost blogPost);
	List<BlogPost> getBlogs(boolean approved);
	BlogPost getBlogById(int id);
}
