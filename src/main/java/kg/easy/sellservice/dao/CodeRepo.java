package kg.easy.sellservice.dao;

import kg.easy.sellservice.models.entities.Code;
import kg.easy.sellservice.models.entities.User;
import kg.easy.sellservice.models.enums.CodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<Code, Long> {

    Code findByUserAndStatus(User user, CodeStatus status);

}
