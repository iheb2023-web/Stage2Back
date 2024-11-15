package com.Stage.CoinVert.controller;
import com.Stage.CoinVert.entity.Admin;
import com.Stage.CoinVert.entity.Client;
import com.Stage.CoinVert.service.AdminService;
import com.Stage.CoinVert.service.ClientService;
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
public class ClientController {
    private ClientService clientService;

    @GetMapping("/User")
    public List<Client> getClient(){return clientService.getClient();}
    @GetMapping("/User/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.getClientById(id).orElseThrow(
                ()->new EntityNotFoundException("Request Client not found")
        );
    }
    @PostMapping("/User")
    public Client addClient(@RequestBody Client cl){
        return clientService.saveClient(cl);
    }
    @PutMapping("/User/{id}")
    public ResponseEntity<?> UpdateClient(@RequestBody Client cl, @PathVariable Long id){
        if(clientService.existeClientById(id)){
            Client cli = clientService.getClientById(id).orElseThrow(()-> new EntityNotFoundException("Request user not found"));
            cli.setNom(cl.getNom());
            cli.setId(cl.getId());
            cli.setAdresse(cl.getAdresse());
            cli.setEmail(cl.getEmail());
            cli.setMotDePasse(cl.getMotDePasse());
            clientService.saveClient(cli);
            return ResponseEntity.ok().body(cli);
        }else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message",id+" user not found or matched");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    @DeleteMapping("/User/{id}")
    public  ResponseEntity<?> deleteClient(@PathVariable Long id){
        if(clientService.existeClientById(id)){
            clientService.deleteClient(id);
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
