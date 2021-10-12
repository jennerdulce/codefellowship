package com.jennerdulce.codefellowship.repository;

import com.jennerdulce.codefellowship.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository  extends JpaRepository<ApplicationUser, Long>{
    ApplicationUser findByUsername(String username);
}
