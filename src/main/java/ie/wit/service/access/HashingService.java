package ie.wit.service.access;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * This class will deal with BCrypt and and password hashing in general
 *
 * @author Joe Wemyss
 */
@Service
class HashingService
{
	/**
	 * Logger for this class
	 */
	private Logger logger = org.slf4j.LoggerFactory.getLogger(LoginService.class);
	private static int workload = 12;
	/**
	 * This compares two passwords and returns a boolean.
	 *
	 * @param passwordPlaintext  plaintext password
	 * @param storedHash  the stored hash from the database
	 * @return true if passwords match, false otherwise
	 */
	 boolean checkPassword(String passwordPlaintext, String storedHash) {
		if(null == storedHash || !storedHash.startsWith("$2a$")) {
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
		}
		logger.debug("Plaintext: " + passwordPlaintext);
		logger.debug("hashed: " + storedHash);
		return(BCrypt.checkpw(passwordPlaintext, storedHash));
	}

	/**
	 * This generates a hashed password from a plaintext string.
	 *
	 * @param plaintext the String to be hashed
	 * @return a hash of the string
	 */
	String generatePasswordHash(String plaintext){
		return BCrypt.hashpw(plaintext, BCrypt.gensalt(workload));
	}
}
