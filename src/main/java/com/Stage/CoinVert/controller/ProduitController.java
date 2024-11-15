package com.Stage.CoinVert.controller;

import lombok.Data;
import com.Stage.CoinVert.entity.Produit;
import com.Stage.CoinVert.service.ProduitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
public class ProduitController {
    private ProduitService produitService;

    @GetMapping("/Prod")
    public List<Produit> getProd(){return produitService.getProd();}
    @GetMapping("/Prod/{id}")
    public Produit getProdById(@PathVariable Long id){
        return produitService.getProdById(id).orElseThrow(
                ()->new EntityNotFoundException("Request Prod not found")
        );
    }
    @PostMapping("/Prod")
    public Produit addProd(@RequestBody Produit produit){
        return produitService.saveProd(produit);
    }
    @PutMapping("/Prod/{id}")
    public ResponseEntity<?> UpdateProd(@RequestBody Produit produit, @PathVariable Long id){
        if(produitService.existeProdById(id)){
            Produit pro1 = produitService.getProdById(id).orElseThrow(()-> new EntityNotFoundException("Request Prod not found"));
            pro1.setLibelle(produit.getLibelle());
            pro1.setDescription(produit.getDescription());
            pro1.setPrix(produit.getPrix());
            pro1.setImage(produit.getImage());
            pro1.setCategorie(produit.getCategorie());
            produitService.saveProd(pro1);
            return ResponseEntity.ok().body(pro1);
        }else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" Produit not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("/Prod/{id}")
    public  ResponseEntity<?> deleteProd(@PathVariable Long id){
        if(produitService.existeProdById(id)){
            produitService.deleteProd(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message","Produit with id "+id+" are deleted ");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }else{
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" Produit not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }

    }
}
