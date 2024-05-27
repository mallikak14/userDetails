package userdetails.service;

import java.util.List;
import java.util.Optional;
import userdetails.entities.*;

public interface UserService {

    public List<User> getAllUsers();

    public Optional<User> getUserById(int userid);

    public void add(User u);

    public void delete(int userid);

    public void update(User u, int userid);

    public Boolean findByUserName(String username);

    public User getUserByNameAndPassword(User user);


}
