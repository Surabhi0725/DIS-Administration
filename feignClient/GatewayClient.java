package sgsits.cse.dis.administration.feignClient;

import javax.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Gateway client.
 */
@FeignClient(name = "gateway")
public interface GatewayClient {

  /**
   * Gets type.
   *
   * @param request the request
   * @return the type
   */
  @RequestMapping(value = "/dis/getUserType", method = RequestMethod.GET)
  String getuserType(@RequestParam HttpServletRequest request);

  /**
   * Gets user id.
   *
   * @param username the username
   * @return the user id
   */
  @RequestMapping(value = "/dis/getUserId", method = RequestMethod.GET)
  String getUserId(@RequestParam("username") String username);

}
