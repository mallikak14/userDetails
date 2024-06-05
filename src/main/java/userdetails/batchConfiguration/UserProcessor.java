package userdetails.batchConfiguration;

import org.springframework.batch.item.ItemProcessor;

import userdetails.entities.User;

public class UserProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User user) throws Exception {
        return user;
       
    }

}
