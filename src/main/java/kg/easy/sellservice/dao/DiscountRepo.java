package kg.easy.sellservice.dao;

import kg.easy.sellservice.models.entities.Discount;
import kg.easy.sellservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {

    List<Discount> findAllByProduct(Product product);

}
