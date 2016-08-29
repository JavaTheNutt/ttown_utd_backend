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
	// TODO: Possible Fix for repetition -- have the package-local methods check for validity and then create a Map of the claims to be verified. The exposed methods could then directly check for the claims that they need using the checkClaims() method.

	/**
	 * Validate if the user is an admin
	 *
	 * @param jwt The JWT in string format
	 * @return true if user has admin role, false otherwise
	 */
	// TODO: Refactor this to check JWT validity and then check for admin claim? Could then remove the method below.
	boolean validateAdmin(String jwt)
	{
		Claims claims = parseJwt(jwt);
		return validateAdminClaim(claims);
	}

	/**
	 * Validate admin claims.
	 *
	 * @param claims the claims contained in the JWT
	 * @return true if user has admin role, false otherwise
	 */
	private boolean validateAdminClaim(Claims claims)
	{
		logger.debug("Checking claims");
		// TODO: Remove unnessecary memory allocation here after testing
		/*boolean jwtValid = validateJwt(claims);
		boolean isAdmin = isAdmin(claims);*/
		return validateJwt(claims) && isAdmin(claims);
	}

	/**
	 * Check if the user has admin rights.
	 *
	 * @param claims the claims extracted from the JWT
	 * @return true if user has admin role, false otherwise
	 */
	private boolean isAdmin(Claims claims)
	{
		logger.debug("Checking for admin authentication");
		return claims.get("auth").equals("ADMIN");
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
	private boolean validateJwt(Claims claims)
	{

		logger.debug("Checking jwt validity");
		Map<String, String> claimsToBeChecked = new HashMap<>();
		claimsToBeChecked.put("iss", "JavaTheNutt");

		return checkClaims(claims, claimsToBeChecked) && checkJwtTime(claims);
	}

	//TODO: Test checkExpiredJwt()

	/**
	 * Check if a JWT is expired or sent before it is allowed.
	 *
	 * @param claims the claims contained in the JWT
	 * @return true if the JWT is within the time constraints, false otherwise
	 */
	private boolean checkJwtTime(Claims claims)
	{
		Date now = new Date(System.currentTimeMillis());
		boolean notBefore = claims.getNotBefore().before(now);
		boolean notExpired = claims.getExpiration().after(now);

		return notBefore && notExpired;
	}

	/**
	 * Gather the claims from the JWT
	 *
	 * @param jwt the jwt that the claims should be gathered from
	 * @return the claims in key => value pairs
	 */
	private Claims parseJwt(String jwt)
	{
		logger.debug("Parsing JWT");
		return Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt).getBody();
	}
}
