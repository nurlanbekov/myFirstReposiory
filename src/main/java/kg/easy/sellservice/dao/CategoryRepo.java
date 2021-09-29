package kg.easy.sellservice.dao;

import kg.easy.sellservice.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {


    Category findByName(String name);

}
