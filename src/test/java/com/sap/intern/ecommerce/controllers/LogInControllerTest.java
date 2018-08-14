package com.sap.intern.ecommerce.controllers;

import com.sap.intern.ecommerce.SpringContext;
import com.sap.intern.ecommerce.dto.UserDTO;
import com.sap.intern.ecommerce.facade.UserFacade;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Base64;

import static org.hamcrest.CoreMatchers.is;


public class LogInControllerTest extends SpringContext {

    @Autowired
    UserFacade userFacade;

    @Test
    public void logIn(){

       final String uri = getBaseUri("/login");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        String plainCreds = "joaomaria@gmail.com:teste";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        headers.add("Authorization", "Basic " + base64Creds);


        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<UserDTO> response = restTemplate.exchange(uri, HttpMethod.GET, request, UserDTO.class);
        UserDTO userDTO = response.getBody();

        UserDTO desired = userFacade.findByEmail("joaomaria@gmail.com");

        Assert.assertTrue(userDTO.equals(desired));

    }

    @Test
    public void token(){
        final String uri = getBaseUri("/token");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        String plainCreds = "joaomaria@gmail.com:teste";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        headers.add("Authorization", "Basic " + base64Creds);


        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        String token = response.getBody();


        Assert.assertTrue(token.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"));

    }

}
