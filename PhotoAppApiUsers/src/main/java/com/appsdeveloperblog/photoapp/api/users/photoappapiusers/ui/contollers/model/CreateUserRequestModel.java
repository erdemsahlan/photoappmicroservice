package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

    @NotNull(message = "First Name cannot be null")
    @Size(min = 2,message  = "first name must not to be  less than two characters")
    private String firstName;
    @NotNull(message = "Last Name cannot be null")
    @Size(min = 2,message  = "lsat name must not to be  less than two characters")
    private String lastName;
    @NotNull(message = "password cannot be null")
    @Size(min = 8,max = 16,message = "password must be equal or grater 8 char and less 16 char")
    private String password;
    @NotNull(message = "email cannot be null")
    @Email
    private String email;




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
}
