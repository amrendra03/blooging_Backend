package com.blooging;

import com.blooging.config.AppConstants;
import com.blooging.entities.Role;
import com.blooging.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BloogingApplication implements CommandLineRunner {

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BloogingApplication.class, args);
		System.out.println("Started....");
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}


	@Override
	public void run(String... args) throws Exception {

		try{
			Role role = new Role();
			role.setId(AppConstants.ADMIN);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL);
			role1.setName("NORMAL_USER");
			List<Role> roles = List.of(role,role1);
			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName());
			});

		}catch (Exception ex){
			ex.printStackTrace();
		}

	}
}
