package com.Stage.CoinVert.controller;
import com.Stage.CoinVert.entity.Admin;
import com.Stage.CoinVert.service.AdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
@Data
public class AdminController {
    private AdminService adminService;

    @GetMapping("/Admin")
    public List<Admin> getAdmin(){return adminService.getAdmin();}
    @GetMapping("/Admin/{id}")
    public Admin getAdminById(@PathVariable Long id){
        return adminService.getAdminById(id).orElseThrow(
                ()->new EntityNotFoundException("Request Client not found")
        );
    }
    @PostMapping("/Admin")
    public Admin addAdmin(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }
    @PutMapping("/Admin/{id}")
    public ResponseEntity<?> UpdateAdmin(@RequestBody Admin admin, @PathVariable Long id){
        if(adminService.existeAdminById(id)){
            Admin cli = adminService.getAdminById(id).orElseThrow(()-> new EntityNotFoundException("Request user not found"));
            cli.setNom(admin.getNom());
            cli.setId(admin.getId());
            cli.setAdresse(admin.getAdresse());
            cli.setEmail(admin.getEmail());
            cli.setMotDePasse(admin.getMotDePasse());
            cli.setRole(admin.getRole());
            adminService.saveAdmin(cli);
            return ResponseEntity.ok().body(cli);
        }else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" user not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("/Admin/{id}")
    public  ResponseEntity<?> deleteAdmin(@PathVariable Long id){
        if(adminService.existeAdminById(id)){
            adminService.deleteAdmin(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message","user with id "+id+" are deleted ");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }else{
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" user not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }

    }
}
