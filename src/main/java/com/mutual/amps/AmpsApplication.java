package com.mutual.amps;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmpsApplication implements CommandLineRunner {

	/* Solo modo desarrollo 
	@Autowired
	private PasswordEncoder passwordEncoder; */
	

	public static void main(String[] args) {
		SpringApplication.run(AmpsApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
	
	
		/* String password = "password";


		for(int i = 0; i < 1; i++){
			String bcryptPassword = this.passwordEncoder.encode(password);
			
			
			System.out.println("#################################################:     ->  " + bcryptPassword);
			
		}  */

	}
	
	


}
