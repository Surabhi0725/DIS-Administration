package sgsits.cse.dis.administration.feignClient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Infrastructure client.
 */
@FeignClient(name = "infrastructure")
public interface InfrastructureClient {

  /**
   * Find incharge of list.
   *
   * @param id the id
   * @return the list
   */
  @RequestMapping(value = "/infrastructure/findIncharge", method = RequestMethod.GET)
  List<String> findInchargeOf(@RequestParam("id") String id);
}
