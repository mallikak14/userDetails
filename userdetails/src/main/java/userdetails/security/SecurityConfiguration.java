package userdetails.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

// @Bean
// 	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//       http .csrf((protection) -> protection.ignoringRequestMatchers(toH2Console())).headers((header) -> header.frameOptions().sameOrigin() );;
//       http.authorizeHttpRequests(auth->auth
//      .requestMatchers(toH2Console()).permitAll());
//    //   .requestMatchers(HttpMethod.POST).hasAuthority("USER").requestMatchers(HttpMethod.GET).hasAuthority("ADMIN"))
//       //.anyRequest().authenticated())
//        // .httpBasic(withDefaults()));
//         return http.build();
//     }


    @Bean
    public UserDetailsService userDetialsService(PasswordEncoder passwordEncoder){
        UserDetails user1=User.builder().username("alexy")
        .password(passwordEncoder.encode("123"))
        .roles("USER").build();

        UserDetails user2=User.builder().username("bob")
        .password(passwordEncoder.encode("abc"))
        .roles("ADMIN").build();

        UserDetails user3=User.builder().username("catty")
        .password(passwordEncoder.encode("xyz"))
        .roles("ADMIN","USER").build();

        return new InMemoryUserDetailsManager(user1,user2,user3);
        
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

 }
