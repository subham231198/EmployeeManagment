package com.example.EmployeeManagement.EmployeeManagement.Repository;

import com.example.EmployeeManagement.EmployeeManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{

    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
//    Optional<User> findByUser_Id(Long user_Id);
}