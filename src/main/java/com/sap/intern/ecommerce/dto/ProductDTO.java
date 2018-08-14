package com.sap.intern.ecommerce.dto;

import java.util.Collection;
import java.util.Objects;

public class ProductDTO {


    private Long id;

    private String name;

    private String description;

    private Collection<PriceDTO> prices;

    public Long getId() {
        return id;
    }

    public ProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Collection<PriceDTO> getPrices() {
        return prices;
    }

    public ProductDTO setPrices(Collection<PriceDTO> prices) {
        this.prices = prices;
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
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
