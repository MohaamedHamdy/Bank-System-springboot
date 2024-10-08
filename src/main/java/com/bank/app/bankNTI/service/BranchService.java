package com.bank.app.bankNTI.service;

import com.bank.app.bankNTI.dto.BranchDto;
import com.bank.app.bankNTI.entity.Branch;
import com.bank.app.bankNTI.entity.User;
import com.bank.app.bankNTI.repository.BranchRepository;
import com.bank.app.bankNTI.success.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
//    Logger logger = LoggerFactory.getLogger(BranchService.class);
    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public ResponseEntity<SuccessResponse> createBranch(BranchDto branchDto) {
        Branch branch = new Branch();
        branch.setBranchName(branchDto.getBranchName());
        branch.setBranchCode(branchDto.getBranchCode());
        branch.setBranchAddress(branchDto.getBranchAddress());

        branchRepository.save(branch);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Branch created successfully");
        successResponse.setCode(HttpStatus.CREATED.toString());

        return ResponseEntity.ok(successResponse);
    }

    public Optional<Branch> getBranchDetails(long branchId) {
        return branchRepository.findById(branchId);
    }

    public List<User> getAllUsersInBranch(Long branchId){
        Branch branch = branchRepository.findById(branchId).orElse(null);
//        System.out.println(branch.getBranchCode());
//        System.out.println("fas");
        return branch.getUsers();
    }

}
