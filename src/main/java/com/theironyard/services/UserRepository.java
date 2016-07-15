package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alexanderhughes on 7/15/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByUsername(String username);
}
