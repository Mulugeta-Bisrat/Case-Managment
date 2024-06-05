package com.acm.casemanagement.repository;



import com.acm.casemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByIdAndIsActiveTrue(Long id ); //just active

//    default Optional<User> getUserById(Long id) {
//        Object userRepository = null;
//        return userRepository.findByIdAndIsActiveTrue(id);


    }

