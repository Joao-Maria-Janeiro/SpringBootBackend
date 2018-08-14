package com.sap.intern.ecommerce.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CART")
public class Cart implements Serializable {

    private static final long serialVersionUID = 6306018349744728360L;


    @Id
    @Column(name = "CART_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "CART_PRODUCTS")
    private Set<Product> products;

    public Cart() {
        products = new HashSet<>();
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Cart setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }


    public Customer getCustomer() {
        return customer;
    }

    public Cart setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cart cart = (Cart) o;
        return active == cart.active &&
                Objects.equals(id, cart.id) &&
                Objects.equals(creationDate, cart.creationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, active, creationDate);
    }


    public Long getId() {
        return id;
    }

    public Cart setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean getActive() {
        return active;
    }

    public Cart setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Cart setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
