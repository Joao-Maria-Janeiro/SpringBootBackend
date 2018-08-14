package com.sap.intern.ecommerce.dto;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Produces("application/json")
@XmlRootElement
public class CartDTO {


    private Long id;

    private Boolean active;

    private Date creationDate;

    private CustomerDTO customer;

    private Set<ProductDTO> products;

    public Long getId() {
        return id;
    }

    public CartDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public CartDTO setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public CartDTO setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public CartDTO setCustomer(CustomerDTO customer) {
        this.customer = customer;
        return this;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public CartDTO setProducts(Set<ProductDTO> products) {
        this.products = products;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDTO cartDTO = (CartDTO) o;
        return Objects.equals(id, cartDTO.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
