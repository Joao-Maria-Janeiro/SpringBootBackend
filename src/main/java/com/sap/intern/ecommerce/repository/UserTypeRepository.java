package com.sap.intern.ecommerce.repository;

import com.sap.intern.ecommerce.model.UserType;
import org.springframework.data.repository.CrudRepository;

public interface UserTypeRepository extends CrudRepository<UserType, Long> {

    UserType findByName(String name);
}
