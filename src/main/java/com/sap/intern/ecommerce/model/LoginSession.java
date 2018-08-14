package com.sap.intern.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "LOGIN_SESSION")
public class LoginSession implements Serializable {


    private static final long serialVersionUID = 4908848209673329906L;

    @Id
    @Column(name = "TOKEN")
    private String token;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginSession that = (LoginSession) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(token);
    }

    public String getToken() {

        return token;
    }

    public LoginSession setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public LoginSession setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public User getUser() {
        return user;
    }

    public LoginSession setUser(User user) {
        this.user = user;
        return this;
    }
}
