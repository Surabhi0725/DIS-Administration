package sgsits.cse.dis.administration.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>ResponseMessage</h1>class.
 * This class is pojo form for return java object as a response to request over network.
 *
 * @author Devyani garg.
 * @since 8 -DEC-2018
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestMessage {

  /**
   * The Message.
   */
  private String message;
}
