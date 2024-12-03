package com.example.jfsd.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "blog_post")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    @ManyToMany
    @JoinTable(
        name = "blog_post_hashtags",
        joinColumns = @JoinColumn(name = "blog_post_id"),
        inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private List<Hashtag> hashtags;

    private String imagePath;

    @ManyToOne
    private Blogger blogger; // Links the post to the blogger who created it

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtagList) {
        this.hashtags = hashtagList;
    }

    public void setImagePath(String imagePath) {
        // Remove the base directory part (src/main/resources/static/images)
        
            this.imagePath = imagePath;
        }
    


    public String getImagePath() {
        return imagePath;
    }

   

    

    public Blogger getBlogger() {
        return blogger;
    }

    public void setBlogger(Blogger blogger) {
        this.blogger = blogger;
    }
}
