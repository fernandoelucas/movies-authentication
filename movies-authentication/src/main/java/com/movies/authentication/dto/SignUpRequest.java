package com.movies.authentication.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @Size(min = 4, max =20)
    private String firstName;

    @Size(min = 4, max =20)
    private String lastName;

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    public SignUpRequest(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public SignUpRequest() {
        //Default Constructor
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignUpRequest signUpRequest = (SignUpRequest) o;

        if (firstName != null ? !firstName.equals(signUpRequest.firstName) : signUpRequest.firstName != null) return false;
        if (lastName != null ? !lastName.equals(signUpRequest.lastName) : signUpRequest.lastName != null) return false;
        if (username != null ? !username.equals(signUpRequest.username) : signUpRequest.username != null) return false;
        if (password != null ? !password.equals(signUpRequest.password) : signUpRequest.password != null) return false;
        return email != null ? email.equals(signUpRequest.email) : signUpRequest.email == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
