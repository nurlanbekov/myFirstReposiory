package kg.easy.sellservice.dao;

import kg.easy.sellservice.models.entities.Price;
import kg.easy.sellservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {

    List<Price> findAllByProduct(Product product);

}
