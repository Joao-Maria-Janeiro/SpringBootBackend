package com.sap.intern.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "USER_TYPES")
public class UserType implements Serializable {
    private static final long serialVersionUID = 596453484185982846L;

    @Id
    @Column(name = "USER_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public UserType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserType setName(String name) {
        this.name = name;
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
        UserType userType = (UserType) o;
        return Objects.equals(id, userType.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
