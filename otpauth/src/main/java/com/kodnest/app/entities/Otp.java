package com.kodnest.app.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int otpid;
	
	@Column
	int otpvalue;
	
	@Column
	LocalDateTime createdat;
	
	@OneToOne
	@JoinColumn(name="uid")
	User user;
	
	public Otp() {
		
	}

	public Otp(int otpid, int otpvalue, LocalDateTime createdat, User user) {
		super();
		this.otpid = otpid;
		this.otpvalue = otpvalue;
		this.createdat = createdat;
		this.user = user;
	}

	public Otp(int otpvalue, LocalDateTime createdat, User user) {
		super();
		this.otpvalue = otpvalue;
		this.createdat = createdat;
		this.user = user;
	}

	public int getOtpid() {
		return otpid;
	}

	public void setOtpid(int otpid) {
		this.otpid = otpid;
	}

	public int getOtpvalue() {
		return otpvalue;
	}

	public void setOtpvalue(int otpvalue) {
		this.otpvalue = otpvalue;
	}

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Otp [otpid=" + otpid + ", otpvalue=" + otpvalue + ", createdat=" + createdat + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdat, otpid, otpvalue, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Otp other = (Otp) obj;
		return Objects.equals(createdat, other.createdat) && otpid == other.otpid && otpvalue == other.otpvalue
				&& Objects.equals(user, other.user);
	}
	
	
}
