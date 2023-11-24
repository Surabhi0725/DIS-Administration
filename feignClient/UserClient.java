package sgsits.cse.dis.administration.feignClient;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sgsits.cse.dis.administration.dto.response.StudentDto;

import java.util.List;

/**
 * The interface User client.
 */
@FeignClient(name = "user")
public interface UserClient {

  /**
   * Gets user id.
   *
   * @param username the username
   * @return the user id
   */
  @RequestMapping(value = "/userFeignClientController/getUserId", method = RequestMethod.GET)
  String getUserId(@RequestParam("username") String username);


  /**
   * Exists by username boolean.
   *
   * @param username the username
   * @return the boolean
   */
  @GetMapping(value = "/userFeignClientController/existsByUsername/{username}")
  boolean existsByUsername(@PathVariable("username") String username);

  @GetMapping(value = "/userFeignClientController/getByUserName/{userid}")
  String getByUserName(@PathVariable("userid") String userid);

  @GetMapping(value = "/userFeignClientController/getUserId/{username}")
  String getUserIdByUserName(@PathVariable("username") String username);

  @GetMapping(value = "/userFeignClientController/getStudentUserNameList")
  List<StudentDto> getStudentUserNameList();

  @GetMapping(value = "/userFeignClientController/getStudentFullNameList")
  List<StudentDto> getStudentFullNameList();

  @GetMapping(value = "/userFeignClientController/getStudentNameByUserId/{userId}")
  StudentDto getNameByUserId(@PathVariable("userId") String userId);
}
