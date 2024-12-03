package com.example.jfsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jfsd.model.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
 Admin findByUsername(String username);
 
}
