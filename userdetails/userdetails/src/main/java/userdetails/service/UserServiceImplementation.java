package userdetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import userdetails.Repos.RoleRepo;
import userdetails.Repos.UserRepo;
import userdetails.entities.Role;
import userdetails.entities.User;
@Slf4j
@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepo repo;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public List<User> getAllUsers() {
        log.info("getting all users");
        List<User> userList = repo.findAll();
        if (!userList.isEmpty())
            return userList;
        return List.of();
    }

    @Override
    public Optional<User> getUserById(int userid){
        log.debug("id {}",userid);
        Optional<User> user = repo.findById(userid);
        log.debug("response{}", user);
        return user;
    }

    @Override
    public int add(User u) {
        Optional<User> data = repo.findById(u.getUserId());
        log.info("checking if the user exists and adding");
        if (data.isPresent())
            return 0;
        else
            repo.save(u);
        return 1;
    }

    @Override
    public int delete(int userid) {
        Optional<User> data = repo.findById(userid);
        log.info("deleting user by id");
        if (data.isPresent()) {
            repo.deleteById(userid);
            return 1;
        } else
            return 0;
    }

    @Override
    public int update(User updateuser, int userid) {
        var data = repo.findById(userid);
        log.info("updating user by id");
        if (data.isPresent()) {
            var existingUser = data.get();
            existingUser.setGmail(updateuser.getGmail());
            repo.save(existingUser);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roleList = roleRepo.findAll();
        if (!roleList.isEmpty())
            return roleList;
        return List.of(); 
    }

    // public String uploadImage(MultipartFile file) throws IOException {

    //     User imageData = repo.save(User.builder()
    //             .name(file.getOriginalFilename())
    //             .type(file.getContentType())
    //             .imageData(ImageUtils.compressImage(file.getBytes())).build());
    //     if (imageData != null) {
    //         return "file uploaded successfully : " + file.getOriginalFilename();
    //     }
    //     return null;
    // }

    // public byte[] downloadImage(String fileName){
    //     Optional<User> dbImageData = repo.findByName(fileName);
    //     byte[] images=ImageUtils.decompressImage(dbImageData.get().getProfilePicture());
    //     return images;
    // }

}
