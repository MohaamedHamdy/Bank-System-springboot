package com.bank.app.bankNTI.controller;

import com.bank.app.bankNTI.dto.BranchDto;
import com.bank.app.bankNTI.entity.Branch;
import com.bank.app.bankNTI.entity.User;
import com.bank.app.bankNTI.exception.BranchNotFound;
import com.bank.app.bankNTI.service.BranchService;
import com.bank.app.bankNTI.success.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {
    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createBranch(@RequestBody BranchDto branchDto) {
        return branchService.createBranch(branchDto);
    }

    @GetMapping("/{branchId}")
    public Branch getBranchDetails(@PathVariable long branchId) {
        return branchService.getBranchDetails(branchId).orElseThrow(BranchNotFound::new);
    }

    @GetMapping("/allUsers/{branchId}")
    public List<User> getAllUsersInBranch(@PathVariable Long branchId) {
        return branchService.getAllUsersInBranch(branchId);
    }

}
