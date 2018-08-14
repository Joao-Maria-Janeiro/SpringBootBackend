package com.sap.intern.ecommerce.bootstrap;

import com.google.common.collect.ImmutableList;
import com.sap.intern.ecommerce.model.*;
import com.sap.intern.ecommerce.repository.CustomerRepository;
import com.sap.intern.ecommerce.repository.ProductRepository;
import com.sap.intern.ecommerce.repository.UserRepository;
import com.sap.intern.ecommerce.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

import static com.sap.intern.ecommerce.utils.Profiles.SAMPLE;

@Component
@Slf4j
@Profile(SAMPLE)
public class ImportUserDataSample implements ImportUserData {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void importData() {
        log.info("Dummy Products - Initializing...");


        final ImmutableList.Builder<User> usersBuilder = ImmutableList.builder();

        final ImmutableList.Builder<UserType> typeBuilder = ImmutableList.builder();

        final ImmutableList.Builder<Customer> customerBuilder = ImmutableList.builder();

        final ImmutableList.Builder<Product> productBuilder = ImmutableList.builder();


        UserType admin = new UserType();
        admin.setName("ADMIN");

        UserType user = new UserType();
        user.setName("CUSTOMER");

        typeBuilder.add(admin);
        typeBuilder.add(user);


        userTypeRepository.saveAll(typeBuilder.build());

        usersBuilder.add(
                new User()
                        .setEmail("joaomaria@gmail.com")
                        .setPassword("teste")
                        .setType(admin)
        );

        usersBuilder.add(
                new User()
                        .setEmail("joaomaria1@gmail.com")
                        .setPassword("teste")
                        .setType(user)
        );


        usersRepository.saveAll(usersBuilder.build());

        customerBuilder.add(
                (Customer) new Customer()
                        .setCountryISO("US")
                        .setName("JOAO")
                        .setEmail("joaomaria6@gmail.com")
                        .setPassword("teste")
                        .setType(user)
        );

        customerRepository.saveAll(customerBuilder.build());

        Collection<Price> prices = new ArrayList<>();
        prices.add(new Price().setCurrencyCode("US").setValue(400D));
        prices.add(new Price().setCurrencyCode("EUR").setValue(900D));

        productBuilder.add(
                new Product()
                        .setDescription("RANDOM")
                        .setName("Telemovel")
                        .setPrices(prices)
                        .setId(1L)
        );

        productBuilder.add(
                new Product()
                        .setDescription("RANDOM2")
                        .setName("Computador")
                        .setPrices(prices)
                        .setId(2L)
        );

        productBuilder.add(
                new Product()
                        .setDescription("RANDOM3")
                        .setName("Headphones")
                        .setPrices(prices)
                        .setId(3L)
        );

        productRepository.saveAll(productBuilder.build());

        log.info("Dummy Products - Initialized!");
    }
}
