package com.sap.intern.ecommerce.repository;

import com.sap.intern.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query(value = "SELECT c FROM Cart c WHERE c.active = TRUE AND c.customer.id = ?1")
    Cart findByCustomerByActiveCart(Long id);

    @Query(value = "SELECT c FROM Cart c WHERE c.customer.id = ?1")
    Iterable<Cart> findAllCartsByCustomer(Long id);
}
