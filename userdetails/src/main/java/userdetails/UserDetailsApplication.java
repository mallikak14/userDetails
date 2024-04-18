package userdetails;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableTransactionManagement
public class UserDetailsApplication {

	public static void main(String[] args) {
		System.out.println("hai");
		SpringApplication.run(UserDetailsApplication.class, args);
	}
}

	