package userdetails.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import userdetails.Repos.GmailRepo;
import userdetails.Repos.UserRepo;
import userdetails.entities.Gmail;
import userdetails.entities.User;
import userdetails.exceptions.GmailNotProvided;
import userdetails.exceptions.UserAlreadyExists;
import userdetails.exceptions.UserNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepo repo;
    @Autowired
    GmailRepo grepo;

    Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
    
   @Scheduled(cron="0 * * * * *")
    @Override
    public List<User> getAllUsers() {
        logger.info("getting all users");
        List<User> userList = repo.findAll();
        System.out.println("fetch service call in"+new Date().toString());
        System.out.println("no of records fetvhed "+userList.size());
        System.out.println("thread name: "+Thread.currentThread().getName());
        logger.info("users: {}",userList);
        if (!userList.isEmpty())
            return userList;
        return List.of();
    }

    @Override
    public Optional<User> getUserById(int userid) {
        try {
            logger.debug("id {}", userid);
            Optional<User> user = repo.findById(userid);
            logger.debug("response{}", user);
            if (user.isPresent()) {
                return user;
            } else {
                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException userNotFoundException) {
            logger.error("user with specified id is not found", userNotFoundException);
            throw userNotFoundException;
        }
    }
    @Transactional
    @Override
    public void add(User u) {
        try {
            logger.info("checking if the user exists and adding");
            if (findByUserName(u.getUserName())) {
                throw new UserAlreadyExists();
            }
            if (u.getGmail() != null && !u.getGmail().isEmpty()) {
                for (Gmail gmail : u.getGmail()) {
                    gmail.setUser(u);
                    grepo.save(gmail);
                }
                repo.save(u);
            } else {
                throw new GmailNotProvided();
            }
        } catch (UserAlreadyExists userAlreadyExists) {
            logger.error("user with same name already exists", userAlreadyExists);
            throw userAlreadyExists;
        } catch (GmailNotProvided gmailNotProvided) {
            logger.error("gmail is not given for input", gmailNotProvided);
            throw gmailNotProvided;
        }
    }

    @Override
    public void delete(int userid) {
        try {
            Optional<User> data = repo.findById(userid);
            logger.info("deleting user by id");
            if (data.isPresent()) {
                repo.deleteById(userid);
            } else {
                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException userNotFoundException) {
            logger.error("user with specified id is not present", userNotFoundException);
            throw userNotFoundException;
        }
    }

    @Override
    @Transactional
    public void update(User updateuser, int userid) {
        try {
            var data = repo.findById(userid);
            logger.info("updating user by id");
            if (data.isPresent()) {
                var existingUser = data.get();
                existingUser.setAge(updateuser.getAge());
                repo.save(existingUser);
            } else {
                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException userNotFoundException) {
            logger.error("user with specified id is not present", userNotFoundException);
            throw userNotFoundException;
        }
    }

    @Override
    public Boolean findByUserName(String username) {
        User u = repo.findByUserName(username);
        if (u != null)
            return true;
        return false;
    }
    @Scheduled(initialDelay = 10000,fixedDelay = 8000)//fixedRate = 10000)
    public void startScheduleMethod(){
        System.out.println("startind schedule"+new Date()+Thread.currentThread().getName());
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    @Async
    public void startScheduleMethod2(){
        System.out.println("startind schedule"+new Date()+Thread.currentThread().getName());
    }
}
