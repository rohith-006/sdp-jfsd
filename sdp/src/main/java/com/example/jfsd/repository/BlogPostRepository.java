package com.example.jfsd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jfsd.model.*;
@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

	List<BlogPost> findByBloggerId(Long id);

	
}
