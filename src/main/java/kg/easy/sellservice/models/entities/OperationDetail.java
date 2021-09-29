package kg.easy.sellservice.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Table(name = "operations_details")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    Product product;

    @ManyToOne
    @JoinColumn(name = "id_operations")
    Operation operation;

    Integer amount;
    Double price;

}
