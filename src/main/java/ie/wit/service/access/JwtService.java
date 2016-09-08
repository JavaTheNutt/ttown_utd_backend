package ie.wit.service.access;

import ie.wit.service.util.exceptions.custom_exceptions.InvalidJwtException;
import ie.wit.service.util.exceptions.custom_exceptions.UserNotAuthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	String requestJwt(String username, String userRole)
	{
		return createJwt(username, userRole);
	}

	/**
	 * Validate if the user is an admin
	 *
	 * @param jwt The JWT in string format
	 * @return true if user has admin role, false otherwise
	 */
	boolean validateAdmin(String jwt)
	{
		Claims claims = parseJwt(jwt);
		Map<String, String> claimsToBeChecked = new HashMap<>();
		claimsToBeChecked.put("iss", "JavaTheNutt");
		claimsToBeChecked.put("auth", "ADMIN");
		return checkJwtTime(claims) && checkClaims(claims, claimsToBeChecked);
	}

	/**
	 * Validate a JWT and return a new one.
	 *
	 * @param oldJwt the JWT to be validated
	 * @return a new JWT
	 */
	String requestNewJwt(String oldJwt, String requiredRole)
	{
		if (isJwtValid(oldJwt, requiredRole)) {
			Map<String, String> details = getUsernameAndRole(oldJwt);
			return requestJwt(details.get("user"), details.get("role"));
		}
		throw new InvalidJwtException();
	}

	/**
	 * return the username and the role of the user that the jwt was issued to
	 *
	 * @param jwt the JWT to be parsed
	 * @return a map containing the username, with key "user", and the role, with key "role"
	 */
	Map<String, String> getUsernameAndRole(String jwt)
	{
		Map<String, String> details = new HashMap<>();
		String username = getValue(jwt, "sub");
		String role = getValue(jwt, "auth");
		details.put("user", username);
		details.put("role", role);
		return details;
	}

	/**
	 * The method that creates the JWT.
	 *
	 * @param username the username to be included in the claim
	 * @param userRole the role of the user in the claim
	 * @return a JWT
	 */
	private String createJwt(String username, String userRole)
	{
		logger.debug("Creating JWT");
		byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secret);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + (60000 * 60);
		Date now = new Date(nowMillis);
		return Jwts.builder()
				.setSubject(username)
				.claim("auth", userRole)
				.setIssuer("JavaTheNutt")
				.setIssuedAt(now)
				.setNotBefore(now)
				.setExpiration(new Date(expMillis))
				.signWith(signatureAlgorithm, secretKeyBytes)
				.compact();
	}

	/**
	 * Switch to route checking the required role to the correct method.
	 *
	 * @param actual the users role.
	 * @param required the minimum role required.
	 * @return true if the user has sufficient privileges, false otherwise
	 */
	private boolean checkRequiredRole(String actual, String  required){
		required = required.toLowerCase();
		switch(required){
			case "admin":
				return isAdmin(actual);
			case "write":
				return isWrite(actual);
			case "read":
				return isRead(actual);
			default:
				return false;
		}
	}

	/**
	 * This method wil check the validity of the claims contained in the JWT.
	 *
	 * @param claims   This will be the claims to be checked.
	 * @param expected This will be a Map of the expected values that will correspond with the claims
	 * @return true if all of the claims match, false otherwise
	 */
	private boolean checkClaims(Claims claims, Map<String, String> expected)
	{
		logger.debug("Checking " + expected.size() + " claims");
		for (Map.Entry<String, String> entry : expected.entrySet()) {
			if (!claims.get(entry.getKey()).equals(entry.getValue())) {
				logger.error("Warning! Invalid entry found for: " + entry.getKey() + " with value: " + entry.getValue()
						+ " Actual value for key: " + entry.getKey() + " was " + claims.get(entry.getKey()));
				throw new UserNotAuthorizedException();
			}
		}
		logger.debug("All claims are valid");
		return true;
	}

	/**
	 * Check the validity of the passed JWT.
	 * .
	 *
	 * @param jwt the JWT to be verified
	 * @return true if it is a valid JWT, false otherwise
	 */
	private boolean isJwtValid(String jwt, String requiredRole)
	{
		logger.info("Checking jwt validity");
		Claims claims = parseJwt(jwt);
		String role = (String) claims.get("auth");
		logger.info("Current users role: " + role);
		Map<String, String> required = new HashMap<>(1);
		required.put("iss", "JavaTheNutt");
		return checkJwtTime(claims) && checkClaims(claims, required) && checkRequiredRole(role, requiredRole);
	}

	/**
	 * Check admin rights.
	 *
	 * @param role the users role
	 * @return true if the user is an admin, false otherwise
	 */
	private boolean isAdmin(String role){
		return role.equalsIgnoreCase("admin");
	}

	/**
	 * Check admin/write privileges.
	 *
	 * @param role the users role
	 * @return true if the user has admin or write privileges, false otherwise
	 */
	private boolean isWrite(String role){
		return isAdmin(role) || role.equalsIgnoreCase("write");
	}

	/**
	 * Check admin/write/read privileges.
	 *
	 * @param role the users role
	 * @return true if the user has admin, write or read privileges, false otherwise
	 */
	private boolean isRead(String role){
		return isWrite(role) || role.equalsIgnoreCase("read");
	}
	/**
	 * Check if a JWT is expired or sent before it is allowed.
	 *
	 * @param claims the claims contained in the JWT
	 * @return true if the JWT is within the time constraints, false otherwise
	 */
	private boolean checkJwtTime(Claims claims)
	{
		Date now = new Date(System.currentTimeMillis());
		return claims.getNotBefore().before(now) && claims.getExpiration().after(now);
	}

	/**
	 * Gather the claims from the JWT
	 *
	 * @param jwt the jwt that the claims should be gathered from
	 * @return the claims in key, value pairs
	 */
	private Claims parseJwt(String jwt)
	{
		logger.debug("Parsing JWT");
		return Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt).getBody();
	}

	/**
	 * This will get a specific value from a JWT if it exists.
	 *
	 * @param jwt           the JWT
	 * @param requiredValue the key of the value that is required
	 * @return the value of the claim specified
	 */
	private String getValue(String jwt, String requiredValue)
	{
		Claims claims = parseJwt(jwt);
		return claims.get(requiredValue, String.class);
	}
}
