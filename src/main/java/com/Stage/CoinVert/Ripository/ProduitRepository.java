package com.Stage.CoinVert.Ripository;

import com.Stage.CoinVert.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
