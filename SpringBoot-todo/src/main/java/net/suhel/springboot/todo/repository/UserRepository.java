package net.suhel.springboot.todo.repository;
import net.suhel.springboot.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>  {
   Optional<User> findByUserName(String username);
   Optional<User> findByUserNameOrEmail(String username, String email);
   Boolean existsByEmail(String email);
}
