package com.rinkesh.schoolmanagementapi.repository;

import com.rinkesh.schoolmanagementapi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author RinkeshKM
 * @Date 27/08/20
 */

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(value = "select id, email, password, status, user_role from users where email = ?1", nativeQuery = true)
    Users findByEmail(String userName);
}
