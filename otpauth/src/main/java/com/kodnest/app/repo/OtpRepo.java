package com.kodnest.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.app.entities.Otp;

@Repository
public interface OtpRepo extends JpaRepository<Otp, Integer>{
 public  Otp findByOtpvalue(int otp);
}
