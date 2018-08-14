package com.sap.intern.ecommerce;

import com.sap.intern.ecommerce.dto.CartDTO;
import com.sap.intern.ecommerce.dto.ProductDTO;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ECommerceBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringContext {

    private static final String BASE_URI = "http://localhost:{0}/{1}";
    private static String token;
    private static String tokenAdmin;

    @LocalServerPort
    private int randomServerPort;

    protected synchronized String getToken() {
        if (token == null) {
            token = requestValidToken("joaomaria6@gmail.com", "teste");
        }

        return token;
    }

    protected synchronized String getTokenAdmin() {
        if (tokenAdmin == null) {
            tokenAdmin = requestValidToken("joaomaria@gmail.com", "teste");
        }

        return tokenAdmin;
    }

    protected String getBaseUri(@NonNull final String path) {
        return MessageFormat.format(BASE_URI, String.valueOf(randomServerPort), path);
    }

    private String requestValidToken(String user, String pass) {
        final String uri = getBaseUri("/token");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        String credentialsPlain = String.join(":", user, pass);

        byte[] credentialsBase64 = Base64.getEncoder().encode(credentialsPlain.getBytes());

        headers.add("Authorization", "Basic " + new String(credentialsBase64));

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

        return response.getBody();
    }

    protected List<Long> mapToProductIds(CartDTO cartDTO) {
        return cartDTO.getProducts().stream()
                .map(ProductDTO::getId)
                .collect(Collectors.toList());
    }

    protected <E> ResponseEntity<E> restCall(Class<E> klass,
                                             HttpMethod method,
                                             String path,
                                             String token) {
        return restCall(klass, method, path, token, EMPTY);
    }

    protected <E> ResponseEntity<E> restCall(Class<E> klass,
                                             HttpMethod method,
                                             String path,
                                             String token,
                                             String body) {

        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token " + token);

        HttpEntity<String> request;

        if (StringUtils.isBlank(body)) {
            request = new HttpEntity<>(headers);
        } else {
            headers.setContentType(MediaType.APPLICATION_JSON);
            request = new HttpEntity<>(body, headers);
        }

        return restTemplate.exchange(getBaseUri(path), method, request, klass);
    }
}
