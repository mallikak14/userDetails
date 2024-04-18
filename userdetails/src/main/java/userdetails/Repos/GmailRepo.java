package userdetails.Repos;
import org.springframework.data.jpa.repository.JpaRepository;

import userdetails.entities.Gmail;

public interface GmailRepo extends JpaRepository<Gmail,Integer>{
    
}
