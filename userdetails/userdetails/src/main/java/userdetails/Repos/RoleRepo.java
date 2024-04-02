package userdetails.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import userdetails.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer>{
    
}
