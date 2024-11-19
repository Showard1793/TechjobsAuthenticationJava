//YOUTUBE LINK:          https://youtu.be/i8Swv5d1IBY?si=uuLHSiWEnr8XuNVR&t=820

package org.launchcode.techjobsauth.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationFormDTO extends LoginFormDTO{

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 30, message = "Password must be 3-30 characters long.")
    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void  setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
