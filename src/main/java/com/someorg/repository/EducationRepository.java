package com.someorg.repository;

import com.someorg.model.Education;
import com.someorg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByUser(User user);
}
