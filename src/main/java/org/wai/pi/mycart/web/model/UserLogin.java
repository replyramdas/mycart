package org.wai.pi.mycart.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserLogin {
	
	@Id
	@GeneratedValue
	private long id;
	
	
	//TODO: temp fix. company and username should be composite primary key	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="company_code")
	private String companyCode;
	
	@Column(name="password")
	private String password;
	
	private String confirmPassword;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="firstlogin")
	private boolean firstTimeLogin;


	public UserLogin() {

	}

	public long getId() {
		return id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	public boolean isFirstTimeLogin() {
		return firstTimeLogin;
	}

	public void setFirstTimeLogin(boolean firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}	
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}	
}
