package com.enigma.api.inventory.entities;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table (name = "transaction")
@Entity
@SQLDelete(sql = "UPDATE transaction SET is_deleted = 1 WHERE id=?")
@Where(clause = "is_deleted=0")
@Getter @Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "customer_name", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;


}
