package userdetails.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import userdetails.entities.User;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
   Optional<User> findByUserName(String userName);
   public User findByUserNameOrPassword(String userName, String password);
}
