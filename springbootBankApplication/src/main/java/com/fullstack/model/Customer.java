package com.fullstack.model;


import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custAccountId;
    @Size(min = 2, message = "Employee Name should be atleast 2 characters")
    private String custName;
    
    @Range(min = 1000000000L, max = 9999999999L, message = "Employee Contact Number Must be 10 Digits")
    @Column(unique = true)
    private long custContactNumber;
    @Email(message = "Email ID must be valid")
    @Column(unique = true)
    private String custEmailId;
    @Size(min = 4, message = "Employee Password should be atleast 4 characters")
    private String custPassword;
    private String custAddress;
    private double custAccountBalance;
    private String otp;

    public int getCustAccountId() {
        return custAccountId;
    }

    public void setCustAccountId(int custAccountId) {
        this.custAccountId = custAccountId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public long getCustContactNumber() {
        return custContactNumber;
    }

    public void setCustContactNumber(long custContactNumber) {
        this.custContactNumber = custContactNumber;
    }

    public String getCustEmailId() {
        return custEmailId;
    }

    public void setCustEmailId(String custEmailId) {
        this.custEmailId = custEmailId;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public double getCustAccountBalance() {
        return custAccountBalance;
    }

    public void setCustAccountBalance(double custAccountBalance) {
        this.custAccountBalance = custAccountBalance;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custAccountId=" + custAccountId +
                ", custName='" + custName + '\'' +
                ", custContactNumber=" + custContactNumber +
                ", custEmailId='" + custEmailId + '\'' +
                ", custPassword='" + custPassword + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custAccountBalance=" + custAccountBalance +
                ", otp='" + otp + '\'' +
                '}';
    }
}
