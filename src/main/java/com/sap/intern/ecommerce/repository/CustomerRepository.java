package com.sap.intern.ecommerce.repository;

import com.sap.intern.ecommerce.model.Customer;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByEmail(@NonNull final String name);

}