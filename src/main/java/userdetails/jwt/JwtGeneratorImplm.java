// package userdetails.jwt;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import userdetails.entities.User;
// @Service
// public class JwtGeneratorImplm implements JwtGenerator {
//     @Value("${jwt.secret}")
//     private String jwtSecret;

//     @Value("${jwt.message}")
//     private String jwtMessage;

//     @Override
//     public Map<String, String> generateToken(User user) {
//         String jwtToken=" ";
//         jwtToken=Jwts.builder().setSubject(user.getUserName())
//         .setIssuedAt(new Date())
//         .signWith(SignatureAlgorithm.HS256,jwtSecret)
//         .compact();
//         Map<String,String> jwtTokenGen=new HashMap<String,String>();
//         jwtTokenGen.put("jwtToken", jwtToken);
//         jwtTokenGen.put("jwtMessage", jwtMessage);
//         return jwtTokenGen;
        
//     }
    
// }
