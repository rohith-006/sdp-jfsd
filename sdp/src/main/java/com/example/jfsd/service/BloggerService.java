package com.example.jfsd.service;



import com.example.jfsd.model.Blogger;

public interface BloggerService {
    Blogger saveBlogger(Blogger blogger);
    Blogger findBloggerByUsername(String username);
    }

