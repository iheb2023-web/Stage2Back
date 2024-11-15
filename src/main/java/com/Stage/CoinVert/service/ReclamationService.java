package com.Stage.CoinVert.service;

import com.Stage.CoinVert.Ripository.ProduitRepository;
import com.Stage.CoinVert.Ripository.ReclamationRepository;
import com.Stage.CoinVert.entity.Produit;
import com.Stage.CoinVert.entity.Reclamation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReclamationService {
    private ReclamationRepository reclamationRepository;

    public List<Reclamation> getReclamation(){
        return reclamationRepository.findAll();
    }

    public Reclamation saveReclamation(Reclamation reclamation){
        return reclamationRepository.saveAndFlush(reclamation);
    }

    public boolean existeReclamationById(long id){
        return reclamationRepository.existsById(id);
    }
    public void deleteReclamation(long id){
        reclamationRepository.deleteById(id);
    }
}
