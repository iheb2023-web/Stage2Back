package com.Stage.CoinVert.service;

import com.Stage.CoinVert.Ripository.DetailCommandeRepository;
import com.Stage.CoinVert.Ripository.ProduitRepository;
import com.Stage.CoinVert.entity.DetailCommande;
import com.Stage.CoinVert.entity.Produit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DetailCommandeService {
    private DetailCommandeRepository detailCommandeRepository;
    private ProduitRepository produitRepository;

    public List<DetailCommande> getDetailCommande(){
        return detailCommandeRepository.findAll();
    }
    public Optional<DetailCommande> getDetailCommandeById(long id){
        return detailCommandeRepository.findById(id);
    }
    public DetailCommande saveDetailCommande(DetailCommande detailCommande){
        Produit produit = produitRepository.findById(detailCommande.getProduit().getId())
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));
        detailCommande.setProduit(produit);
        return detailCommandeRepository.saveAndFlush(detailCommande);
    }
    public boolean existeDetailCommandeById(long id){
        return detailCommandeRepository.existsById(id);
    }
    public void deleteDetailCommande(long id){
        detailCommandeRepository.deleteById(id);
    }
}
