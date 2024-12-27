package com.fullstack.service;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

	public int otp() {
		// TODO Auto-generated method stub
		return (int) (Math.random() * 9000 + 1000);
	}
}
