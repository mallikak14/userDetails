package userdetails.Repos;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import userdetails.entities.Gmail;

public interface GmailRepo extends JpaRepository<Gmail,Integer>{
    // Optional<Gmail> findByGmail(List<Gmail> list); 
}
