package com.enigma.api.inventory.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Table (name = "customer")
@Entity
@SQLDelete(sql = "UPDATE customer SET is_deleted = 1 WHERE id=?")
@Where(clause = "is_deleted=0")
public class Customer extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name", length = 30, unique = true)
    private String customerName;

    @Column (name= "customer_address", length = 40)
    private String address;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;

    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
