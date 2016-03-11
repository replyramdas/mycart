package org.wai.pi.mycart.web.util;

import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String[] passwords = {"rsawant","sashi","feroz","laxmi"};
		for(String pass: passwords){
			System.out.println(passwordEncoder.encode(pass));
		}
		Arrays.stream(passwords).forEach(passwordEncoder::encode);
	}
}
