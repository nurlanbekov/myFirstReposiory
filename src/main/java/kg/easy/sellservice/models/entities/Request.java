package kg.easy.sellservice.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "requests")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    boolean success;

    @CreationTimestamp
    Date addDate;

    @ManyToOne
    @JoinColumn(name = "id_codes")
    Code code;

}
