package com.example.jfsd.controller;

import com.example.jfsd.model.BlogPost;
import com.example.jfsd.model.Blogger;  // Import Blogger model
import com.example.jfsd.repository.BlogPostRepository;
import com.example.jfsd.repository.BloggerRepository;
import com.example.jfsd.service.AdminService;
import com.example.jfsd.service.BlogPostService;
import com.example.jfsd.service.BloggerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.example.jfsd.model.Admin;

import java.io.IOException;
import java.util.List;

@Controller
public class BloggerController {

    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private BloggerRepository bloggerRepository;
    @Autowired
    private BlogPostRepository blogPostRepository;
    

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private AdminService adminservice;
    
    
    @GetMapping("/adminsignup")
    public String showadminsignup() {
    	return "adminsignup";
    }
    
    @PostMapping("/createadminsignup")
    public String createadminaccount(@RequestParam String username , @RequestParam String password,Model model) {
    	Admin admin =new Admin();
    	admin.setUsername(username);
    	admin.setPassword(password);
    	adminservice. saveAdmin(admin);
    	model.addAttribute("message","Account Created Succesfully!,Please Log In");
    	return "redirect:/adminlogin";
    	
    }
    @GetMapping("/adminlogin")
    public String showadminlogin(@RequestParam (required =false)String error,Model model) {
    	if(error !=null) {
    		model.addAttribute("error",error);
    		
    	}
    	return "adminlogin";
    }
    @PostMapping("/adminlogin")
    public String adminlogin(@RequestParam String username, @RequestParam String password,Model model) {
    	Admin admin =adminservice.findadminbyusername(username);
    	if(admin !=null && admin.getPassword().equals(password)){
    		model.addAttribute("username",username);
    		model.addAttribute("password",password);
    		return "redirect:/adminhome?username=" +username;
    	}else {
    		return "redirect:/adminlogin?error=Invalid username or password";
    	}
    }
    @GetMapping("/ad")
    public String showpage(@RequestParam String username,Model model) {
    	model.addAttribute("username",username);
    	return "AdmnHome";
    }
   
    
    
    
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // Points to signup.jsp
    }
   
    @GetMapping("/adminhomepage")
    public String showadminhomepage(@RequestParam ("username") String username , @RequestParam ("user")String user ,Model model) {
  	  Admin admin =adminservice.findadminbyusername(username);
        Blogger blogger = bloggerService.findBloggerByUsername(user);

  	  if(admin == null) {
  		  model.addAttribute("error","Admin Not found");
  		  return "error";
  		  }
  	  
  	  List <BlogPost> blogosts =blogPostService.getPostsByBlogger(blogger.getId());
  	System.out.println("Blogposts: " + blogosts);
  	  for (BlogPost post : blogosts) {
            if (post.getImagePath() != null && post.getImagePath().startsWith("src")) {
                String correctedPath = post.getImagePath().substring(post.getImagePath().indexOf("static"));
                post.setImagePath(correctedPath); // Update the image path to be relative
            }
        }
  	 model.addAttribute("username", username); 
     model.addAttribute("user",user);
        model.addAttribute("blogposts", blogosts);
       
        // Ensure 'username' is set

        return "AdmnHome"; // Points to BloggerHome.jsp
    
    }

    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String username, @RequestParam String password, Model model) {
        Blogger blogger = new Blogger();
        blogger.setUsername(username);
        blogger.setPassword(password);

        bloggerService.saveBlogger(blogger);
        model.addAttribute("message", "Account created successfully! Please log in.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "login"; // Points to login.jsper
    }
   
    

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Blogger blogger = bloggerService.findBloggerByUsername(username);

        if (blogger != null && blogger.getPassword().equals(password)) {
            model.addAttribute("username", username);
            model.addAttribute("bloggerId", blogger.getId());  // Optionally store bloggerId for future use
            return "redirect:/bloggerHome?username=" + username;  // Use username instead of bloggerId
        } else {
            return "redirect:/login?error=Invalid username or password";
        }
    }
  
    
    @GetMapping("/bloggerHome")
    public String showBloggerHomePage(@RequestParam String username, Model model) {
        Blogger blogger = bloggerService.findBloggerByUsername(username);
        if (blogger == null) {
            model.addAttribute("error", "Blogger not found!");
            return "error";  // Show error page if blogger is not found
        }
         
        List<BlogPost> blogPosts = blogPostService.getPostsByBlogger(blogger.getId());
      	System.out.println("Blogposts: " + blogPosts);

        
        // Correct the image path if it includes the full system path
        for (BlogPost post : blogPosts) {
            if (post.getImagePath() != null && post.getImagePath().startsWith("src")) {
                String correctedPath = post.getImagePath().substring(post.getImagePath().indexOf("static"));
                post.setImagePath(correctedPath); // Update the image path to be relative
            }
        }
        
        model.addAttribute("blogPosts", blogPosts);
        model.addAttribute("username", username); // Ensure 'username' is set

        return "BloggerHome"; // Points to BloggerHome.jsp
    }

    @GetMapping("/addPost")
    public String showAddPostPage(@RequestParam String username, Model model) {
        System.out.println("Received username in /addPost: " + username);
        model.addAttribute("username", username);  // Pass the username to the view
        return "addpost"; // Points to the addpost.jsp page
    }

    @PostMapping("/savePost")
    public String savePost(@RequestParam String username, @RequestParam String title,
                           @RequestParam String description, @RequestParam String hashtags,
                           @RequestParam MultipartFile image, Model model) {
        System.out.println("savePost invoked");
        System.out.println("Username: " + username);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Hashtags: " + hashtags);
        System.out.println("Image: " + (image != null ? image.getOriginalFilename() : "No image uploaded"));

        try {
            Blogger blogger = bloggerService.findBloggerByUsername(username);
            if (blogger == null) {
                System.out.println("Blogger not found for username: " + username);
                model.addAttribute("error", "Blogger not found!");
                return "redirect:/addPost";  
            }

            BlogPost blogPost = new BlogPost();
            blogPost.setTitle(title);
            blogPost.setDescription(description);
            blogPost.setBlogger(blogger);

            blogPostService.savePost(blogPost, image, hashtags);

            System.out.println("Post created successfully!");
            return "redirect:/bloggerHome?username=" + username;
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error saving the post.");
            return "redirect:/addPost";
        }
    }

    @GetMapping("/posts/{username}")
    @ResponseBody  // Ensures response is sent as JSON
    public List<BlogPost> getPostsByUsername(@PathVariable String username) {
        Blogger blogger = bloggerRepository.findByUsername(username);
        if (blogger != null) {
            return blogPostRepository.findByBloggerId(blogger.getId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blogger not found");
        }
    }
}
