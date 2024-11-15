package com.Stage.CoinVert.service;
import com.Stage.CoinVert.Ripository.AdminRepository;
import com.Stage.CoinVert.entity.Admin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {
    private AdminRepository adminRepository;

    public List<Admin> getAdmin(){
        return adminRepository.findAll();
    }
    public Optional<Admin> getAdminById(long id){
        return adminRepository.findById(id);
    }
    public Admin saveAdmin(Admin admin){
        return adminRepository.saveAndFlush(admin);
    }
    public boolean existeAdminById(long id){
        return adminRepository.existsById(id);
    }
    public void deleteAdmin(long id){
        adminRepository.deleteById(id);
    }
}
