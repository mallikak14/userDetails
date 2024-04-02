package userdetails.service;

import java.util.List;
import java.util.Optional;
import userdetails.entities.*;

public interface UserService {

    public List<User> getAllUsers();

    public Optional<User> getUserById(int userid);

    public int add(User u);

    public int delete(int userid);

    public int update(User u, int userid);

    public List<Role> getAllRoles();

}
