package com.enigma.api.inventory.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table (name = "stock")
@Entity
@SQLDelete(sql = "UPDATE stock SET is_deleted = 1 WHERE id=?")
@Where(clause = "is_deleted=0")
@Getter @Setter
public class Stock extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer quantity;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;

    }

}