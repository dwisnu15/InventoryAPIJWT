package com.enigma.api.inventory.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table (name = "item")
@Entity
@SQLDelete(sql = "UPDATE item SET is_deleted = 1 WHERE id=?")
@Where(clause = "is_deleted=0")
@Getter
@Setter
public class Item extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Long price;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "original_filename")
    private String originalFileName;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }



}
