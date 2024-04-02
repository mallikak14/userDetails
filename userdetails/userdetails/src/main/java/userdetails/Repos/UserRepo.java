package userdetails.Repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import userdetails.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
   // Optional<User> findByName(String fileName);

}
