package sgsits.cse.dis.administration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource request not found exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class ResourceRequestNotFoundException extends RuntimeException {


  /**
   * Instantiates a new Resource request not found exception.
   *
   * @param message the message
   */
  public ResourceRequestNotFoundException(String message) {
    super(message);
    log.error("Resource Request not found: {}", message);
  }

  /**
   * Instantiates a new Resource request not found exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public ResourceRequestNotFoundException(String message, Throwable cause) {
    super(message, cause);
    log.error("Resource Request not found: {}", message);
  }
}
