package com.StudentAssignment.AssignmentSubmissionApp.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.StudentAssignment.AssignmentSubmissionApp.Repository.UserRepository;


@Service
public class UserDetailsImp implements UserDetailsService {

    private final UserRepository userRepository;

    

    public UserDetailsImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("User not Found")); 
    }
    
}
