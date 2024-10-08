package com.bank.app.bankNTI.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {
    @NotBlank(message = "Name can't be empty")
//    @NonNull
    private String userName;

    @NotBlank(message = "Address can't be empty")
    private String address;

    @NotBlank(message = "Phone Number can't be empty")
    private String phoneNumber;

    @NotBlank(message = "Email can't be empty")
    private String email;

    @NotBlank(message = "Branch id can't be empty")
    private long branchId;
}
