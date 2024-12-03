package com.example.jfsd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jfsd.model.Admin;
import com.example.jfsd.repository.AdminRepository;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired 
	private AdminRepository adminrepository;

	@Override
	public Admin saveAdmin(Admin admin) {
		
		return adminrepository.save(admin);
	}

	@Override
	public Admin findadminbyusername( String username) {
		
		return adminrepository.findByUsername(username);
	}
 
}
