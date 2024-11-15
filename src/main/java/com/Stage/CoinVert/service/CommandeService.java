package com.Stage.CoinVert.service;

import com.Stage.CoinVert.Ripository.CommandeRepository;
import com.Stage.CoinVert.Ripository.DetailCommandeRepository;
import com.Stage.CoinVert.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommandeService {
    private CommandeRepository commandeRepository;
    private DetailCommandeRepository detailCommandeRepository;

    public List<Commande> getCommande(){
        return commandeRepository.findAll();
    }
    public Optional<Commande> getCommandeById(long id){
        return commandeRepository.findById(id);
    }
    public Commande saveCommande(Commande commande){

        List<DetailCommande> detailsCommande = commande.getDetailsCommande();
        if (detailsCommande != null) {
            for (DetailCommande detailCommande : detailsCommande) {
                detailCommande.setCommande(commande);
            }
        }

        return commandeRepository.saveAndFlush(commande);
    }
    public boolean existeCommandeById(long id){
        return commandeRepository.existsById(id);
    }
    public void deleteCommande(long id){
        commandeRepository.deleteById(id);
    }
}
