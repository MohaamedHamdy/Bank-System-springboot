package com.bank.app.bankNTI.service;

import com.bank.app.bankNTI.dto.UserDto;
import com.bank.app.bankNTI.entity.Branch;
import com.bank.app.bankNTI.entity.User;
import com.bank.app.bankNTI.exception.BranchNotFound;
import com.bank.app.bankNTI.exception.UserNotFound;
import com.bank.app.bankNTI.repository.BranchRepository;
import com.bank.app.bankNTI.repository.UserRepository;
import com.bank.app.bankNTI.success.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BranchRepository branchRepository;

    @Autowired
    public UserService(UserRepository userRepository, BranchRepository branchRepository) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
    }


    public ResponseEntity<SuccessResponse> createUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(String.valueOf(userDto.getPhoneNumber()));

        long branchId = userDto.getBranchId();
//        System.out.println("Branch ID: " + branchId);

        if (branchId > 0) {
            Optional<Branch> branchOpt = branchRepository.findById(branchId);
//            System.out.println("Branch found: " + branchOpt.get());
            if (branchOpt.isPresent()) {
                Branch branch = branchOpt.get();
                user.setBranch(branch);
            } else {
                throw new BranchNotFound();
            }
        }
//        System.out.println(user);

        userRepository.save(user);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("User created successfully");
        successResponse.setCode(HttpStatus.CREATED.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    public User getUserDetails(long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFound::new);
    }
}
