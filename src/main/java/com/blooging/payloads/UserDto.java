package com.blooging.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDto {
	
	private int id;

	@NotNull
	@Size(min = 2,max = 10,message = "User name must be greater than 0 char and less than 10 char" )
	private String name;

	@Email (message = "Email is not valid")
	private String email;

	@NotNull
	@Size(min = 2,message = "Password must be greater than 2 char")
	private String password;

	@NotNull
	@Size(min = 2,message = "About must be greater than 2 char")
	private String about;

}
