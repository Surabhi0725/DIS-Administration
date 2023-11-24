package sgsits.cse.dis.administration.exception;


import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sgsits.cse.dis.administration.dto.response.ExceptionResponseJSON;

/**
 * <h1>AdministrationExceptionHandler</h1> class.
 * This class is exception handler containing handler for different exceptions.
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2019
 */
@RestControllerAdvice
public class AdministrationExceptionHandlerImpl {

  /**
   * Event does not exist exception response entity.
   *
   * @param request   the request
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(
      value = {
          EventDoesNotExistException.class,
          ResourceRequestNotFoundException.class,
          MyFileNotFoundException.class
      }
  )
  @ResponseBody
  public ResponseEntity<ExceptionResponseJSON> eventDoesNotExistException(
      HttpServletRequest request, Exception exception) {
    return new ResponseEntity<>(
        new ExceptionResponseJSON(request.getRequestURL().toString(), exception.getMessage(),
            HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
  }

  /**
   * Conflict ec exception response entity.
   *
   * @param request   the request
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler({ConflictException.class})
  @ResponseBody
  public ResponseEntity<ExceptionResponseJSON> conflictException(HttpServletRequest request,
      ConflictException exception) {
    return new ResponseEntity<>(
        new ExceptionResponseJSON(request.getRequestURL().toString(), exception.getMessage(),
            HttpStatus.CONFLICT), HttpStatus.CONFLICT);
  }

  /**
   * Resource request not accessible exception response entity.
   *
   * @param request   the request
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler({ResourceRequestNotAccessibleException.class})
  @ResponseBody
  public ResponseEntity<ExceptionResponseJSON> resourceRequestNotAccessibleException(
      HttpServletRequest request,
      ResourceRequestNotAccessibleException exception) {
    return new ResponseEntity<>(
        new ExceptionResponseJSON(request.getRequestURL().toString(), exception.getMessage(),
            HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
  }

  /**
   * Parse exception response entity.
   *
   * @param request   the request
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler({ParseException.class})
  @ResponseBody
  public ResponseEntity<ExceptionResponseJSON> parseException(HttpServletRequest request,
      EventDoesNotExistException exception) {
    return new ResponseEntity<>(
        new ExceptionResponseJSON(request.getRequestURL().toString(), exception.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}

