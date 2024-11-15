package com.Stage.CoinVert.service;

import com.Stage.CoinVert.Ripository.ProduitRepository;
import com.Stage.CoinVert.entity.Produit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProduitService {
    private ProduitRepository produitRepository;

    public List<Produit> getProd(){
        return produitRepository.findAll();
    }
    public Optional<Produit> getProdById(long id){
        return produitRepository.findById(id);
    }
    public Produit saveProd(Produit produit){
        return produitRepository.saveAndFlush(produit);
    }
    public boolean existeProdById(long id){
        return produitRepository.existsById(id);
    }
    public void deleteProd(long id){
        produitRepository.deleteById(id);
    }
}
