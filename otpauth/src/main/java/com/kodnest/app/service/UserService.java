package com.kodnest.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kodnest.app.entities.Otp;
import com.kodnest.app.entities.User;
import com.kodnest.app.repo.OtpRepo;
import com.kodnest.app.repo.UserRepo;

@Service
public class UserService {


	
	UserRepo userRepo;
	OtpRepo otpRepo;
	
	JavaMailSender mailSender;

	public UserService(UserRepo userRepo, JavaMailSender mailSender, OtpRepo otpRepo) {
		super();
		this.userRepo = userRepo;
		this.mailSender = mailSender;
		this.otpRepo = otpRepo;
	}
	
	public boolean userSignUp(User user) {
		User savedUser = userRepo.save(user);
		if(savedUser!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean userSignIn(String username, String password) {
		
	User user =	userRepo.findByUsername(username);
	
	if(user!=null) {
		if(user.getPassword().equals(password)) {
			
			//Generate 6 Digit OTP
			
		int otp = new Random().nextInt(100000, 900000);
			System.out.println(otp);
			
			Otp newotp = new Otp(otp, LocalDateTime.now(), user);
			otpRepo.save(newotp);
			
			
			//Send It To User Mail Id
		
		String usermail = user.getEmail();
		
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(usermail);
		message.setSubject("KODNEST OTP");
		message.setText("YOUR LOGIN OTP FOR KODNEST APP IS " + otp);
		mailSender.send(message);
		
			
			
			return true;
		}
	}
		
		return false;
	}
	
	public User verifyOtp(int otp) {
		Otp ref = otpRepo.findByOtpvalue(otp);
		
		if(ref != null && otp == ref.getOtpvalue()) {
		User user = 	userRepo.findById(ref.getUser().getId()).orElseGet(null);
		
		//check the difference in the current time v/s otp created at time
		
		LocalDateTime otpTime = ref.getCreatedat();
		LocalDateTime currentTime = LocalDateTime.now();
		
		// if time difference is less than 1 minute then return user else return null

		if(ChronoUnit.MINUTES.between(otpTime, currentTime) < 1) {
			return user;
		}
		
		else {
			otpRepo.delete(ref);
			return null;
		}
			
		}
		
		return null;
		
	}
}
