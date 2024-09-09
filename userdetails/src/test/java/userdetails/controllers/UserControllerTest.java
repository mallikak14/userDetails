package userdetails.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import userdetails.entities.User;
import userdetails.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService service; 

    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getUsers_withData() {
        User user1 = new User();
        User user2 = new User();
        List<User> userList = List.of(user1, user2);
        when(service.getAllUsers()).thenReturn(userList);

        ResponseEntity<?> response = userController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void test_getUsers_noData() {

        when(service.getAllUsers()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = userController.getUsers();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void test_getUsersbyId(){
        User u=new User();
        u.setUserId(1);
        u.setUserName("mallika");
        when(service.getUserById(1)).thenReturn(Optional.of(u));

        ResponseEntity<?> response= userController.getUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(u), response.getBody());
    }

    @Test
    void test_getUserById_notfound(){
        when(service.getUserById(1)).thenReturn(Optional.empty());
        ResponseEntity<?> response= userController.getUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.empty(), response.getBody());

    }

    @Test
    void test_adduser(){
        User u=new User();
        u.setUserId(1);
        u.setUserName("mallika");

        doNothing().when(service).add(u);

        ResponseEntity<?> response = userController.addUser(u);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("user added successfully", response.getBody());
        assertNotNull(u);
    }

    @Test
    void test_deleteuser(){
        User u=new User();
        u.setUserId(1);
        u.setUserName("mallika");

        doNothing().when(service).delete(1);

        ResponseEntity<?> response = userController.deleteUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("user deleted", response.getBody());
    }

    @Test
    void test_updateuser(){
        User u=new User();
        u.setUserId(1);
        u.setAge(22);
        u.setUserName("mallika");

        doNothing().when(service).update(u, 1);

        ResponseEntity<?> response = userController.updateUser(u, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("user updated", response.getBody());    }
}
