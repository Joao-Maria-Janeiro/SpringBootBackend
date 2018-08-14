package com.sap.intern.ecommerce.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends User {


    private static final long serialVersionUID = -4839444624360362869L;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "COUNTRY_ISO")
    private String countryISO;

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountryISO() {
        return countryISO;
    }

    public Customer setCountryISO(String countryISO) {
        this.countryISO = countryISO;
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
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }
}
