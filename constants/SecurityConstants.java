package sgsits.cse.dis.administration.constants;

import java.util.regex.Pattern;

/**
 * The type Security constants.
 */
public class SecurityConstants {

  /**
   * The constant HEADER_STRING.
   */
  public static final String HEADER_STRING = "Authorization";

  /**
   * The constant TOKEN_PREFIX.
   */
  public static final String TOKEN_PREFIX = "Bearer ";

  /**
   * The constant SECRET_KEY.
   */
  public static final String SECRET_KEY = "jwtDisSecretKey";

  /**
   * The constant STUDENT.
   */
  public static final String STUDENT = "student";

  /**
   * The constant FACULTY.
   */
  public static final String FACULTY = "faculty";

  /**
   * The constant HEAD.
   */
  public static final String HEAD = "head";

  /**
   * The constant DEFAULT_FACULTY_REQUEST_HANDLER.
   */
  public static final String DEFAULT_FACULTY_REQUEST_HANDLER = "pbamne";

  /**
   * The constant MINIMUM_LENGTH_IN_HEADERS.
   */
  public static final Integer MINIMUM_LENGTH_IN_HEADERS = 2;

  /**
   * The constant VALID_JWT_REGEX.
   */
  public static final Pattern VALID_JWT_REGEX =
      Pattern.compile("^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$");

  /**
   * The constant UNAUTHORIZED_MESSAGE.
   */
  public static final String UNAUTHORIZED_MESSAGE =
      "You are not authorized to access this resource";
}
