package springtxdemo.hibernate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_name")
    private String account_name;

    @Column(name = "balance")
    private double balance;

    public Account(String account_name, double balance) {
        this.account_name = account_name;
        this.balance = balance;
    }
}
