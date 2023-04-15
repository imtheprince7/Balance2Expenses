package com.b2c.repository;

import com.b2c.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRegistration,Integer> {

}
