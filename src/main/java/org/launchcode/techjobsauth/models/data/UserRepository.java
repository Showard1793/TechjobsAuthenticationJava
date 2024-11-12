package org.launchcode.techjobsauth.models.data;

import org.launchcode.techjobsauth.models.Job;
import org.launchcode.techjobsauth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // Automatically generates the query: SELECT * FROM User WHERE username = ?
    Optional<User> findByUsername(String username);
}