package org.firstonlineuniversity.utils;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value = "utils")
public class Utils {

	static final Logger logger = Logger.getLogger(Utils.class);

	public String getHashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		logger.info("Hashed Password: " + hashedPassword);
		return hashedPassword;
	}

	public String confirmationString() {
		String uuid = UUID.randomUUID().toString();
		System.out.println("uuid = " + uuid);
		return uuid;
	}
	
	public UUID getUuid() {
		UUID uuid = UUID.randomUUID();
		System.out.println("uuid = " + uuid);
		return uuid;
	}
	
	public static void main(String args[]){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("admin@123");

		logger.info("Hashed Password: " + hashedPassword);
	}
}
