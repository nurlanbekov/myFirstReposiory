package kg.easy.sellservice.models.entities;

import kg.easy.sellservice.models.enums.CodeStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "codes")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    @CreationTimestamp
    Date startDate;
    Date endDate;
    @Enumerated(value = EnumType.STRING)
    CodeStatus status;
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user;

}
