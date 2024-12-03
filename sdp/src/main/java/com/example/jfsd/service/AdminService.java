package com.example.jfsd.service;

import com.example.jfsd.model.Admin;

public interface AdminService {
 Admin saveAdmin(Admin admin) ;
 Admin findadminbyusername(String username);
 
}
