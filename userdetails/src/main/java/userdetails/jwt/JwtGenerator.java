package userdetails.jwt;

import java.util.Map;

import userdetails.entities.User;

public interface JwtGenerator {
    public Map<String,String> generateToken(User user);
    
}
