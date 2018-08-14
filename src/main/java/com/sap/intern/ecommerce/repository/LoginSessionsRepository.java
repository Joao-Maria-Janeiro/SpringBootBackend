package com.sap.intern.ecommerce.repository;

import com.sap.intern.ecommerce.model.LoginSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginSessionsRepository extends CrudRepository<LoginSession, Long> {

    LoginSession findByToken(String token);

}
