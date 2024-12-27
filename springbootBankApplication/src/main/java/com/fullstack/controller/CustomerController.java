package com.fullstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fullstack.model.Customer;
import com.fullstack.service.CustomerServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bank")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;

	// @signUp
	@PostMapping("/signup")
	public ResponseEntity<Customer> signUp(@RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.signUp(customer));
	}

	// @signIn
	@PostMapping("/signin")
	public ResponseEntity<Boolean> signIn(@RequestParam String custEmailId, @RequestParam String custPassword) {
		return ResponseEntity.ok(customerService.signIn(custEmailId, custPassword));
	}

	// @deposit
	@PostMapping("/deposit")
	public ResponseEntity<Customer> deposit(@RequestParam int custAccountId, @RequestParam Double amount) {
		return ResponseEntity.ok(customerService.deposit(custAccountId, amount));
	}

	// @withdraw
	@PostMapping("/withdraw")
	public ResponseEntity<Customer> withdraw(@RequestParam int custAccountId, @RequestParam Double amount) {
		return ResponseEntity.ok(customerService.withdraw(custAccountId, amount));
	}

	// Update Contact Number
	@PutMapping("/update/contact/{custAccountId}")
	public ResponseEntity<Customer> updateContactNumber(@PathVariable int custAccountId,
			@RequestBody long custContactNumber) {
		return ResponseEntity.ok(customerService.updateContactNumber(custAccountId, custContactNumber));
	}

	// Update Address
	@PutMapping("/update/address/{custAccountId}")
	public ResponseEntity<Customer> updateAddress(@PathVariable int custAccountId, @RequestBody String custAddress) {
		return ResponseEntity.ok(customerService.updateAddress(custAccountId, custAddress));
	}

	// Update Email
	@PutMapping("/update/email/{custAccountId}")
	public ResponseEntity<Customer> updateEmail(@PathVariable int custAccountId, @RequestBody String custEmailId) {
		return ResponseEntity.ok(customerService.updateEmail(custAccountId, custEmailId));
	}

	// @deleteAcc
	@DeleteMapping("/deletebyid/{custAccountId}")
	public ResponseEntity<String> deleteById(@PathVariable int custAccountId) {
		customerService.deleteById(custAccountId);
		return ResponseEntity.ok("Account deleted successfully");
	}

	@DeleteMapping("/deleteall")
	public ResponseEntity<String> deleteAll() {
		customerService.deleteAll();
		return ResponseEntity.ok("All accounts deleted successfully");
	}

	@GetMapping("/findbyid/{custAccountId}")
	public ResponseEntity<Optional<Customer>> findById(@PathVariable int custAccountId) {
		return ResponseEntity.ok(customerService.findById(custAccountId));
	}

	@GetMapping("/findall")
	public ResponseEntity<List<Customer>> fndAll() {
		return ResponseEntity.ok(customerService.findAll());
	}

	@PostMapping("/logout/{custAccountId}")
	public ResponseEntity<String> logout(@PathVariable int custAccountId) {
		customerService.logout(custAccountId);
		return ResponseEntity.ok("Logout Successfully");
	}

	@GetMapping("/generate-otp/{custAccountId}")
    public ResponseEntity<String> generateOtp(@PathVariable int custAccountId) {
        try {
            int otp = customerService.generateOtpForTransfer(custAccountId);
            return ResponseEntity.ok("Generated OTP: " + otp);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to generate OTP");
        }
    }

    @PutMapping("/transfer/{custAccountId}")
    public ResponseEntity<String> transfer(@PathVariable int custAccountId, @RequestParam double amount,
                                           @RequestParam int otp) {
        try {
            String result = customerService.transferWithOtp(custAccountId, amount, otp);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer failed: " + e.getMessage());
        }
    }

}
