package com.example.jfsd.service;

import com.example.jfsd.model.BlogPost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogPostService {
    BlogPost savePost(BlogPost blogPost, MultipartFile image, String hashtags) throws IOException;
    List<BlogPost> getPostsByBlogger(Long bloggerId);
}
