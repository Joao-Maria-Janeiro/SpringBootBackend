package com.sap.intern.ecommerce.dto;

import java.util.Objects;

public class PriceDTO {

    private Long id;
    private String currencyCode;
    private Double value;

    public Long getId() {
        return id;
    }

    public PriceDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public PriceDTO setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public PriceDTO setValue(Double value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDTO priceDTO = (PriceDTO) o;
        return Objects.equals(id, priceDTO.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
