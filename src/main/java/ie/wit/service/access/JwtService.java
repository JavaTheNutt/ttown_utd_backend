package ie.wit.service.access;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * This class generates JWT's and also validates them
 *
 * @author Joe Wemyss
 */
@Service
class JwtService
{
	/**
	 * The algorithim used to sign the JWT.
	 */
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	
	/**
	 * The logger for this class.
	 */
	private Logger logger = LoggerFactory.getLogger(JwtService.class);

	/**
	 * The secret key for signing the JWT.
	 */
	@Value("jwt.secret.name")
	private String secret;

	/**
	 * public interface to request a JWT.
	 * 
	 * @param username the username to be included in the claim
	 * @param userRole the role of the user in the claim
	 * @return a JWT
	 */
	String requestJwt(String username, String userRole){
		return createJwt(username, userRole);
	}

	/**
	 * The method that creates the JWT.
	 * 
	 * @param username the username to be included in the claim
	 * @param userRole the role of the user in the claim
	 * @return a JWT
	 */
	private String createJwt(String username, String userRole){
		logger.debug("Creating JWT");
		byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secret);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + (60000 * 60);
		Date now = new Date(nowMillis);
		String jwt = Jwts.builder()
				.setSubject(username)
				.claim("auth", userRole)
				.setIssuer("JavaTheNutt")
				.setIssuedAt(now)
				.setNotBefore(now)
				.setExpiration(new Date(expMillis))
				.signWith(signatureAlgorithm, secretKeyBytes)
				.compact();
		return jwt;
	}

	// FIXME: 28/08/2016 REFACTOR THIS!!!! TOO REPETETIVE.
	/**
	 * Validate if the user is an admin
	 *
	 * @param jwt The JWT in string format
	 * @return true if user has admin role, false otherwise
	 */
	boolean validateAdmin(String jwt){
		Claims claims = parseJwt(jwt);
		return validateAdminClaim(claims);
	}

	/**
	 * Validate admin claims.
	 *
	 * @param jwt the JWT string to be parsed
	 * @return true if user has admin role, false otherwise
	 */
	private boolean validateAdminClaim(Claims claims){
		logger.debug("Checking claims");
		// TODO: Remove unnessecary memory allocation here after testing
		boolean jwtValid = validateJwt(claims);
		boolean isAdmin = isAdmin(claims);
		return jwtValid && isAdmin;
 	}

	/**
	 * Check if the user has admin rights.
	 *
	 * @param claims the claims extracted from the JWT
	 * @return true if user has admin role, false otherwise
	 */
	private boolean isAdmin(Claims claims){
		logger.debug("Checking for admin authentication");
		return claims.get("auth").equals("ADMIN");
	}
	
	//TODO: TEST THIS!!!
	//TODO: May need to refactor the hash map as not all values will be strings. May need to be T ? Object
	/**
	 * This method wil check the validity of the claims contained in the JWT. 
	 * 
	 * @param claims  This will be the claims to be checked.
	 * @param expected  This will be a Map of the expected values that will correspond with the claims
	 * @return  true if all of the claims match, false otherwise
	 */
	private boolean checkClaims(Claims claims, Map<String, String> expected){
		logger.debug("Checking " + expected.size() + " claims");
		for(Map.Entry<String, String> entry : expected.entrySet()){
			if(claims.get(entry.getKey()) != entry.getValue()){
				logger.error("Warning! Invalid entry found for: " + entry.getKey() + " with value: " + entry.getValue());
				return false;
			}
		}
		logger.debug("All claims are valid");
		return true;
	}
	
	/**
	 * This method will check if a JWT is valid.
	 * 
	 * @param claims the JWT to be validated
	 * @return true if jwt is valid, false otherwise
	 */
	private boolean validateJwt(Claims claims){
		long nowMillis = System.currentTimeMillis();
		logger.debug("Checking jwt validity");
		Map<String, String> claimsToBeChecked = new HashMap<>();
		claimsToBeChecked.put("iss", "JavaTheNutt");
		/*
		Uncomment this to test the new implementation above
		return checkClaims(claims, claimsToBeChecked) && claims.getExpiration().after(new Date(nowMillis));
		*/
		return claims.getIssuer().equals("JavaTheNutt") && claims.getExpiration().after(new Date(nowMillis));
	}

	/**
	 * Gather the claims from the JWT
	 * @param jwt the jwt that the claims should be gathered from
	 * @return the claims in key => value pairs
	 */
	private Claims parseJwt(String jwt){
		logger.debug("Parsing JWT");
		return Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt).getBody();
	}
}
