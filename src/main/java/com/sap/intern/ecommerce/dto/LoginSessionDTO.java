package com.sap.intern.ecommerce.dto;

import java.util.Date;
import java.util.Objects;

public class LoginSessionDTO {


    private String token;

    private Date expirationDate;

    private UserDTO user;

    public String getToken() {
        return token;
    }

    public LoginSessionDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public LoginSessionDTO setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public UserDTO getUser() {
        return user;
    }

    public LoginSessionDTO setUser(UserDTO user) {
        this.user = user;
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
        LoginSessionDTO that = (LoginSessionDTO) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(token);
    }
}
