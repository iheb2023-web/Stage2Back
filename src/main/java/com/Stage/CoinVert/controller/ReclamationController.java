package com.Stage.CoinVert.controller;

import com.Stage.CoinVert.entity.Produit;
import com.Stage.CoinVert.entity.Reclamation;
import com.Stage.CoinVert.service.ProduitService;
import com.Stage.CoinVert.service.ReclamationService;
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
public class ReclamationController {
    private ReclamationService reclamationService;

    @GetMapping("/Reclamation")
    public List<Reclamation> getReclamation(){
        return reclamationService.getReclamation();
    }
    @PostMapping("/Reclamation")
    public Reclamation addReclamation(@RequestBody Reclamation reclamation){
        return reclamationService.saveReclamation(reclamation);
    }

    @DeleteMapping("/Reclamation/{id}")
    public ResponseEntity<?> deleteReclamation(@PathVariable Long id){
        if(reclamationService.existeReclamationById(id)){
            reclamationService.deleteReclamation(id);
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
