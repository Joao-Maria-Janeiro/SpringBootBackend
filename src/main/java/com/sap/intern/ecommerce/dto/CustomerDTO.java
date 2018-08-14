package com.sap.intern.ecommerce.dto;

import java.util.Objects;

public class CustomerDTO extends UserDTO {

    private String name;

    private String CountryISO;

    public String getName() {
        return name;
    }

    public CustomerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountryISO() {
        return CountryISO;
    }

    public CustomerDTO setCountryISO(String countryISO) {
        CountryISO = countryISO;
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
        if (!super.equals(o)) {
            return false;
        }
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }
}
