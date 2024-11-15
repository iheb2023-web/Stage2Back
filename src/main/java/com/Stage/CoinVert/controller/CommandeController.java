package com.Stage.CoinVert.controller;

import com.Stage.CoinVert.entity.Commande;
import com.Stage.CoinVert.entity.Produit;
import com.Stage.CoinVert.service.CommandeService;
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
public class CommandeController {
    private CommandeService commandeService;

    @GetMapping("/Commande")
    public ResponseEntity<List<Commande>> getCommande() {
        List<Commande> commandes = commandeService.getCommande();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }
    @GetMapping("/Commande/{id}")
    public Commande getCommandeById(@PathVariable Long id){
        return commandeService.getCommandeById(id).orElseThrow(
                ()->new EntityNotFoundException("Request commande not found")
        );
    }
    @PostMapping("/Commande")
    public Commande addCommande(@RequestBody Commande commande){
        return commandeService.saveCommande(commande);
    }
    @PutMapping("/Commande/{id}")
    public ResponseEntity<?> UpdateCommande(@RequestBody Commande commande, @PathVariable Long id){
        if(commandeService.existeCommandeById(id)){
            Commande cmd = commandeService.getCommandeById(id).orElseThrow(()-> new EntityNotFoundException("Request commande not found"));
            cmd.setId(commande.getId());
            cmd.setDateCommande(commande.getDateCommande());
            cmd.setStatut(commande.getStatut());
            commandeService.saveCommande(cmd);
            return ResponseEntity.ok().body(cmd);
        }else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" commande not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("/Commande/{id}")
    public  ResponseEntity<?> deleteCommande(@PathVariable Long id){
        if(commandeService.existeCommandeById(id)){
            commandeService.deleteCommande(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message","Commande with id "+id+" are deleted ");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }else{
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" Commande not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }

    }
}
