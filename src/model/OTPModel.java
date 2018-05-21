package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tbl_otp")
public class OTPModel {
	
	@Id
	@GenericGenerator(name="otp",strategy="increment")
	@GeneratedValue(generator="otp")
	@Column(name="Id")
	private long id;
	
	@Column(name="OTP")
	private int otp;
	
	@Column(name="Email")
	private String mail;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
