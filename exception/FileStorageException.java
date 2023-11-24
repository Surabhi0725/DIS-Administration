package sgsits.cse.dis.administration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type File storage exception.
 */
@Slf4j
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException {

  /**
   * Instantiates a new File storage exception.
   *
   * @param message the message
   */
  public FileStorageException(String message) {
    super(message);
    log.error("FileStorageException: " + message);
  }

  /**
   * Instantiates a new File storage exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public FileStorageException(String message, Throwable cause) {
    super(message, cause);
    log.error("FileStorageException: " + message);
  }
}