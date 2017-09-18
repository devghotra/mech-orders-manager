package com.jassboys.mech.orders.repo;

import com.jassboys.mech.orders.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}
