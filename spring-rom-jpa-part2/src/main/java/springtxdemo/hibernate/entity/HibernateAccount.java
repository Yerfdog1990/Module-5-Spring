package springtxdemo.hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HibernateAccount {
    @Id
    private int id;
    private Double balance;
}
