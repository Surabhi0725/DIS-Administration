package sgsits.cse.dis.administration.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import org.springframework.stereotype.Component;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;


/**
 * The type Jwt resolver utils.
 */
@Component
public class JwtResolverUtils {

  /**
   * Gets user name from jwt token.
   *
   * @param authHeader the auth header
   * @return the user name from jwt token
   */
  public String getUserNameFromAuthHeader(String authHeader) {
    String token = extractToken(authHeader);
    return getClaimFromToken(token, Claims::getSubject);
  }

  /**
   * Gets id from jwt token.
   *
   * @param authHeader the auth header
   * @return the id from jwt token
   */
  public String getIdFromAuthHeader(String authHeader) {
    String token = extractToken(authHeader);
    return getClaimFromToken(token, Claims::getId);
  }

  /**
   * Gets jwt.
   *
   * @param authHeader the auth header
   * @return the jwt
   * @throws ResourceRequestNotAccessibleException the resource request not accessible exception
   */
  public String extractToken(final String authHeader) throws ResourceRequestNotAccessibleException {
    if (Objects.isNull(authHeader)) {
      throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
    }
    final String[] authHeaderList = authHeader.split(" ");
    if (authHeaderList.length != SecurityConstants.MINIMUM_LENGTH_IN_HEADERS) {
      throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
    }
    if (authHeaderList[0].equals(SecurityConstants.TOKEN_PREFIX)) {
      throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
    }
    final Matcher matcher =
        SecurityConstants.VALID_JWT_REGEX.matcher(authHeaderList[1]);
    if (!matcher.find()) {
      throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
    }
    return authHeaderList[1];
  }

  /**
   * Gets user type from jwt token.
   *
   * @param authHeader the auth header
   * @return the user type from jwt token
   */
  public String getUserTypeFromAuthHeader(String authHeader) {
    String token = extractToken(authHeader);
    return getClaimFromToken(token, Claims::getAudience);
  }

  /**
   * Gets claim from token.
   *
   * @param <T>            the type parameter
   * @param token          the token
   * @param claimsResolver the claims resolver
   * @return the claim from token
   */
  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  /**
   * Gets all claims from token. for retrieving any information from token we will need the secret
   * key
   *
   * @param token the token
   * @return the all claims from token
   */
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(SecurityConstants.SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
  }

  /**
   * Gets expiration date from token.
   *
   * @param token the token
   * @return the expiration date from token
   */
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  /**
   * Is token expired boolean.
   *
   * @param token the token
   * @return the boolean
   */
  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }
}