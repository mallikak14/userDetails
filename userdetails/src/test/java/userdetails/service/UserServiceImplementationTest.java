package userdetails.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;  
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import userdetails.Repos.GmailRepo;
import userdetails.Repos.UserRepo;
import userdetails.entities.Gmail;
import userdetails.entities.User;
import userdetails.exceptions.GmailNotProvided;
import userdetails.exceptions.UserAlreadyExists;
import userdetails.exceptions.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplementationTest {

    @Mock
    UserRepo urepo;
    @Mock
    GmailRepo grepo;
    @InjectMocks
    UserServiceImplementation uservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        User mockUser = new User(1, "alex", 12, 153.43, true, new Date(2000, 9, 14), 9949L);
        when(urepo.findById(1)).thenReturn(Optional.of(mockUser));
        Optional<User> result = uservice.getUserById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getUserId());
    }

    @Test
    void testGetnoUserById() {
        when(urepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            uservice.getUserById(1);});
    }

    @Test
    public void test_getAllUsers() {
        List<User> data1 = new ArrayList<User>();
        data1.add(new User("alex", 12, 153.43, true, new Date(2000, 9, 14), 9949L));
        when(urepo.findAll()).thenReturn(data1);
        List<User> result = uservice.getAllUsers();
        assertEquals(data1, result);
    }

    @Test
    public void test_getAllUsers_emptyCondition() {
        List<User> data1 = new ArrayList<User>();
        when(urepo.findAll()).thenReturn(data1);
        List<User> result = uservice.getAllUsers();
        assertEquals(data1, result);
    }

    @Test
    public void delete_byuserid(){
        User user1 = new User(1,"alex", 12, 153.43, true, new Date(2000, 9, 14), 9949L);
        int userid=user1.getUserId();
        Optional<User> userData = Optional.of(user1);
        when(urepo.findById(userid)).thenReturn(userData);
        doNothing().when(urepo).deleteById(userid);
        uservice.delete(userid);
        verify(urepo).deleteById(userid);        
    }

    @Test
    public void test_delete_userNotFound() {
        when(urepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            uservice.delete(1);
        });
        verify(urepo).findById(1);
    }

    @Test
    public void test_update_userFound() {
        int userId = 1;
        User updateUser = new User("alex", 12, 153.43, true, new Date(2000, 9, 14), 9949L);
        User existingUser = new User("alex", 10, 150.00, true, new Date(2000, 9, 14), 9949L);
        Optional<User> userOptional = Optional.of(existingUser);
        when(urepo.findById(userId)).thenReturn(userOptional);
        when(urepo.save(existingUser)).thenReturn(existingUser);
        uservice.update(updateUser, userId);
        verify(urepo).findById(userId);
        verify(urepo).save(existingUser);
    }

    @Test
    public void test_update_userNotFound() {
        int userId = 1;
        User updateUser = new User("alex", 12, 153.43, true, new Date(2000, 9, 14), 9949L);
        when(urepo.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            uservice.update(updateUser, userId);
        });
        verify(urepo).findById(userId);
    }

    @Test
    public void test_add_userAlreadyExists() {
        User user1 = new User("alex", 12, 153.43, true, new Date(2000, 9, 14), 9949L);
        Optional<User> userData = Optional.of(user1);
        when(urepo.findByUserName(user1.getUserName())).thenReturn(userData);
        assertThrows(UserAlreadyExists.class, () -> {
            uservice.add(user1);
        });
        verify(urepo).findByUserName(user1.getUserName());
    }

    @Test
    public void test_add_gmailNotProvided_forEmptyList() {
        User user1 = new User();
        user1.setUserName("john");
        user1.setGmail(new ArrayList<>());
        Exception ex=assertThrows(GmailNotProvided.class, () -> {
            uservice.add(user1);
        });
        assertEquals("Gmail list cannot be empty or null", ex.getMessage());        
    }

    @Test
    public void test_add_gmailNotProvided_forNull() {
        User user1 = new User();
        user1.setGmail(null);
        Exception exception = assertThrows(GmailNotProvided.class, () -> {
            uservice.add(user1);
        });
        assertEquals("Gmail list cannot be empty or null", exception.getMessage());
    }

    @Test
    public void test_add_gmail() {
        User user1 = new User();
        user1.setUserName("john");
        List<Gmail> gmails = new ArrayList<>();
        gmails.add(new Gmail("john@gmail.com"));
        user1.setGmail(gmails);
        when(urepo.save(user1)).thenReturn(user1);
        uservice.add(user1);
        assertEquals("john",user1.getUserName());
    }
}
