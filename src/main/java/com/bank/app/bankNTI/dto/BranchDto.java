package com.bank.app.bankNTI.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class BranchDto {
    @NotBlank(message = "Branch name can't be empty")
    private String branchName;
    @NotBlank(message = "Branch code can't be empty")
    private String branchCode;
    @NotBlank(message = "Branch address can't be empty")
    private String branchAddress;
}
