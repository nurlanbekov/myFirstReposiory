package kg.easy.sellservice.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "prices")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    Date startDate;
    Date endDate;

    @ManyToOne
    @JoinColumn(name = "id_products")
    Product product;

}
