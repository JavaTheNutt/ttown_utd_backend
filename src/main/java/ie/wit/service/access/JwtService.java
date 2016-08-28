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
		byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secret);
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + (60000 * 60);
		Date now = new Date(nowMillis);
		String jwt = Jwts.builder()
				.setSubject(username)
				.claim("Authentication", userRole)
				.setIssuer("JavaTheNutt")
				.setIssuedAt(now)
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
		return validateAdminClaim(jwt);
	}

	/**
	 * Validate admin claims.
	 *
	 * @param jwt the JWT string to be parsed
	 * @return true if user has admin role, false otherwise
	 */
	private boolean validateAdminClaim(String jwt){
		Claims claims = parseJwt(jwt);
		return validateJwt(claims) && isAdmin(claims);
 	}

	/**
	 * Check if the user has admin rights.
	 *
	 * @param claims the claims extracted from the JWT
	 * @return true if user has admin role, false otherwise
	 */
	private boolean isAdmin(Claims claims){
		return claims.get("Authentication").equals("Admin");
	}

	/**
	 * This method will check if a JWT is valid.
	 * 
	 * @param claims the JWT to be validated
	 * @return true if jwt is valid, false otherwise
	 */
	private boolean validateJwt(Claims claims){
		long nowMillis = System.currentTimeMillis();
		return claims.getIssuer().equals("JavaTheNutt") && claims.getExpiration().after(new Date(nowMillis));
	}

	/**
	 * Gather the claims from the JWT
	 * @param jwt the jwt that the claims should be gathered from
	 * @return the claims in key => value pairs
	 */
	private Claims parseJwt(String jwt){
		return Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(jwt).getBody();
	}
}