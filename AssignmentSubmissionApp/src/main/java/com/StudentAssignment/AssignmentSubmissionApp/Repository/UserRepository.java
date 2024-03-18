package com.StudentAssignment.AssignmentSubmissionApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentAssignment.AssignmentSubmissionApp.Model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByUsername(String username);

}
