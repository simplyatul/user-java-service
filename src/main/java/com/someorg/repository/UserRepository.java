package com.someorg.repository;

import com.someorg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// String is the type of emailId (primary key in User).
public interface UserRepository extends JpaRepository<User, String> {
}
