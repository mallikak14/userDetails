package userdetails.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import userdetails.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
   Optional<User> findByUserName(String userName);
   public User findByUserNameOrPassword(String userName, String password);
}
