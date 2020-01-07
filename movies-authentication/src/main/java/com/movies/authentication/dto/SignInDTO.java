package com.movies.authentication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInDTO {
	
	
    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
    
    public SignInDTO() {
    	
    }    

	public SignInDTO(@NotBlank @Size(min = 5, max = 20) String username,
			@NotBlank @Size(min = 8, max = 20) String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    
}
