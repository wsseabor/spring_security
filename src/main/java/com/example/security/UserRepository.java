package com.example.security;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * This class is also not much changed except for the @Query annotation. Most
 * of the SQL querying will be done in the respective repositories for courses
 * and majors etc, so filtering can be done through that. 
 * 
 * Ex. 
 * 
 * @Query("SELECT c FROM availableCourse c WHERE c.campus = ?1 ")
 * public availableCourse findByCampus(String campus);
 * 
 * Something to that affect. Native SQL queries can be substituted for JPA queries by adding "...nativeQuery = true" 
 * appended to the @Query after the statement.
 */

public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
    
}
