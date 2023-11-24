package sgsits.cse.dis.administration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource request not accessible exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class ResourceRequestNotAccessibleException extends RuntimeException {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Resource request not accessible exception.
     *
     * @param message the message
     */
    public ResourceRequestNotAccessibleException(String message) {
    super(message);
    log.error("Resource request not accessible {}", message);
  }
}