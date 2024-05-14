// package userdetails.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import userdetails.service.UserService;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // import org.springframework.security.web.SecurityFilterChain;

// // @Configuration
// // @EnableWebSecurity
// // public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

// // 	@Bean
// // 	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// // 		http.csrf().disable();
// // 		http.authorizeHttpRequests().anyRequest().authenticated();
// // 	// 	 http.authorizeHttpRequests(auth->{ auth.requestMatchers("/").permitAll();
// // 	// auth.anyRequest().authenticated();
// // 	// });
// // 	//http.formLogin(withDefaults());
// // 	//http.oauth2Login();
// // 	return http.build();
// // 	}
// // }
// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

// 	@Autowired
// 	private UserService userDetailsService;

// 	@Override
// 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
// 	}

// 	@Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http.csrf().disable();

// 		http.authorizeRequests().antMatchers("/rest/**").authenticated().anyRequest().permitAll().and()
// 				.authorizeRequests().antMatchers("/secure/**").authenticated().anyRequest().hasAnyRole("ADMIN").and()
// 				.formLogin().permitAll();

// 	}

// 	@Bean
// 	public BCryptPasswordEncoder encodePWD() {
// 		return new BCryptPasswordEncoder();
// 	}
// }
