package com.fullstack.service;

import com.fullstack.model.Customer;
import com.fullstack.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OtpService otpService;

	// signup
	public Customer signUp(Customer customer) {
		return customerRepository.save(customer);
	}

	// signin
	public boolean signIn(String custEmailId, String custPassword) {
		Customer customer = customerRepository.findByCustEmailIdAndCustPassword(custEmailId, custPassword);
		return customer != null;
	}

	// deposit

	public Customer deposit(int custAccountId, Double amount) {
		Customer customer = customerRepository.findByCustAccountId(custAccountId)
				.orElseThrow(() -> new RuntimeException("Customer Account does not exists:" + custAccountId));
		customer.setCustAccountBalance(customer.getCustAccountBalance() + amount);
		return customerRepository.save(customer);
	}

	// withdraw
	public Customer withdraw(int custAccountId, Double amount) {
		Customer customer = customerRepository.findByCustAccountId(custAccountId)
				.orElseThrow(() -> new RuntimeException("Customer Account does not exists:" + custAccountId));
		if (customer.getCustAccountBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		customer.setCustAccountBalance(customer.getCustAccountBalance() - amount);
		return customerRepository.save(customer);
	}

	// Update Contact Number
	public Customer updateContactNumber(int custAccountId, long custContactNumber) {
		Customer customer = customerRepository.findByCustAccountId(custAccountId)
				.orElseThrow(() -> new RuntimeException("Customer Account does not exists:" + custAccountId));
		customer.setCustContactNumber(custContactNumber);
		return customerRepository.save(customer);
	}

	// Update Address
	public Customer updateAddress(int custAccountId, String customerAddress) {
		Customer customer = customerRepository.findByCustAccountId(custAccountId)
				.orElseThrow(() -> new RuntimeException("Account does not found"));
		customer.setCustAddress(customerAddress);
		return customerRepository.save(customer);
	}

	// Update Email
	public Customer updateEmail(int custAccountId, String customerEmail) {
		Customer customer = customerRepository.findByCustAccountId(custAccountId)
				.orElseThrow(() -> new RuntimeException("Customer Account does not exists:" + custAccountId));
		customer.setCustEmailId(customerEmail);
		return customerRepository.save(customer);
	}

	// @deleteAcc
	public void deleteById(int custAccountId) {
		customerRepository.deleteById(custAccountId);
	}

	public void deleteAll() {
		customerRepository.deleteAll();
	}

	public Optional<Customer> findById(int custAccountId) {
		return customerRepository.findById(custAccountId);
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	// @logout

	public void logout(int custAccountId) {
		// Fetch customer to ensure the account exists
		Customer customer = customerRepository.findByCustAccountId(custAccountId)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found"));
	}

	// transaction

	private int generatedOtp;

	public int generateOtpForTransfer(int custAccountId) {
		customerRepository.findByCustAccountId(custAccountId)
				.orElseThrow(() -> new RuntimeException("Customer account not found: " + custAccountId));

		
		generatedOtp = otpService.otp();
		System.out.println("Generated OTP for account " + custAccountId + ": " + generatedOtp);
		return generatedOtp;
	}

	public String transferWithOtp(int custAccountId, double amount, int enteredOtp) {
		
		if (enteredOtp != generatedOtp) {
			throw new IllegalArgumentException("Invalid OTP.");
		}

		Customer customer = customerRepository.findByCustAccountId(custAccountId)
				.orElseThrow(() -> new RuntimeException("Customer account not found: " + custAccountId));

		if (customer.getCustAccountBalance() < amount) {
			throw new IllegalArgumentException("Insufficient funds.");
		}

		customer.setCustAccountBalance(customer.getCustAccountBalance() - amount);
		customerRepository.save(customer);

		return "Transfer successful!";
	}

	public Optional<Customer> findByName(String custName) {
		return customerRepository.findByCustName(custName);
	}
}
