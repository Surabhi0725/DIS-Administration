package sgsits.cse.dis.administration.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Event does not exist exception.
 */
@Slf4j
public class EventDoesNotExistException extends Exception {

  /**
   * Instantiates a new Event does not exist exception.
   *
   * @param message the message
   */
  public EventDoesNotExistException(String message) {
    super(message);
    log.error("Event does not exist exception: " + message);
  }

}
