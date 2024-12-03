package com.example.jfsd.service;




import com.example.jfsd.model.Blogger;
import com.example.jfsd.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    private BloggerRepository bloggerRepository;

    @Override
    public Blogger saveBlogger(Blogger blogger) {
        // Perform additional validations if needed
        return bloggerRepository.save(blogger);
    }

    @Override
    public Blogger findBloggerByUsername(String username) {
        return bloggerRepository.findByUsername(username);
    }
   

}
