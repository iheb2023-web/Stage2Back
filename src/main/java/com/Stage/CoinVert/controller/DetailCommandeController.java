package com.Stage.CoinVert.controller;

import com.Stage.CoinVert.entity.DetailCommande;
import com.Stage.CoinVert.entity.Produit;
import com.Stage.CoinVert.service.DetailCommandeService;
import com.Stage.CoinVert.service.ProduitService;
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
public class DetailCommandeController {
    private DetailCommandeService detailCommandeService;

    @GetMapping("/DetCmd")
    public List<DetailCommande> getDetailCommande(){return detailCommandeService.getDetailCommande();}
    @GetMapping("/DetCmd/{id}")
    public DetailCommande getDetailCommandeById(@PathVariable Long id){
        return detailCommandeService.getDetailCommandeById(id).orElseThrow(
                ()->new EntityNotFoundException("Request DetailCommande not found")
        );
    }
    @PostMapping("/DetCmd")
    public DetailCommande addDetailCommande(@RequestBody DetailCommande detailCommande){
        return detailCommandeService.saveDetailCommande(detailCommande);
    }
    @PutMapping("/DetCmd/{id}")
    public ResponseEntity<?> UpdateDetailCommande(@RequestBody DetailCommande detailCommande, @PathVariable Long id){
        if(detailCommandeService.existeDetailCommandeById(id)){
            DetailCommande dt = detailCommandeService.getDetailCommandeById(id).orElseThrow(()-> new EntityNotFoundException("Request detail not found"));
            dt.setId(detailCommande.getId());
            dt.setPrixUnitaire(detailCommande.getPrixUnitaire());
            dt.setQuantite(detailCommande.getQuantite());
            detailCommandeService.saveDetailCommande(dt);
            return ResponseEntity.ok().body(dt);
        }else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" DetailCommande not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("/DetCmd/{id}")
    public  ResponseEntity<?> deleteDetailCommande(@PathVariable Long id){
        if(detailCommandeService.existeDetailCommandeById(id)){
            detailCommandeService.deleteDetailCommande(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message","DetailCommande with id "+id+" are deleted ");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }else{
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" DetailCommande not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }

    }
}
