package com.someorg.repository;

import com.someorg.model.ProfessionalExperience;
import com.someorg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionalExpRepository extends JpaRepository<ProfessionalExperience, Long> {
    List<ProfessionalExperience> findByUser(User user);
}
