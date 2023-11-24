package sgsits.cse.dis.administration.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * <h1>ConflictException</h1> class.
 * This class is exception calss for the conflicting entries in the database.
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2019
 */
@Slf4j
public class ConflictException extends Exception {

  /**
   * Instantiates a new Conflict exception.
   *
   * @param message the message
   */
  public ConflictException(String message) {
    super(message);
    log.error("ConflictException: " + message);
  }
}
