package com.backend_challenge.questions.q6.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Size(min = 11, max = 11, message = "Please provide your identityNumber")
    @NotNull(message = "Please provide your identityNumber")
    private String identityNumber;

    @Size(max = 50)
    @NotNull(message = "Please provide your first name")
    private String fullName;

    @Email(message = "Please provide valid email")
    @Size(min = 5, max = 50)
    @NotNull(message = "Please provide your email")
    private String email;

    @Size(min = 4, max = 20,message="Please Provide Correct Size for Password")
    @NotNull(message = "Please provide your password")
    private String password;

    @NotNull(message="Please provide phone number")
    @Size(min=12, max=12,message="Phone number '${validatedValue}' must be {max} chars long")
    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$",message = "Please provide valid phone number")
    private String phoneNumber;

    @NotNull(message = "Please provide your salary")
    private Double salary;

}
