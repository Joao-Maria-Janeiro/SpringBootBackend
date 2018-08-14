package com.sap.intern.ecommerce.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class ProductGetDTO {

    private Long id;

    private String name;

    private String description;

    private PriceDTO price;

    public Long getId() {
        return id;
    }

    public ProductGetDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductGetDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductGetDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public PriceDTO getPrice() {
        return price;
    }

    public ProductGetDTO setPrice(PriceDTO price) {
        this.price = price;
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
        ProductGetDTO that = (ProductGetDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("description", description)
                .append("price", price)
                .toString();
    }
}
