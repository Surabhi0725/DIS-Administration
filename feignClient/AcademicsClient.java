package sgsits.cse.dis.administration.feignClient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <h1><b>AcademicsClient</b> interface.</h1>
 * <p>This interface contains reference to controller "academicsFeignClientController" to
 * ensure communication with <b>academics</b> microservice.
 *
 * @author Arjit Mishra.
 * @version 1.0.
 * @since 2 -DEC-2019.
 */
@FeignClient(name = "academics")
public interface AcademicsClient {

  /**
   * Gets all subject acronym.
   *
   * @return the all subject acronym
   */
  @GetMapping(value = "/academicsFeignClientController/getAllSubjectAcronyms")
  List<String> getAllSubjectAcronym();

  /**
   * Gets course id by name.
   *
   * @param name the name
   * @return the course id by name
   */
  @GetMapping(value = "/academicsFeignClientController/getCourseIdByName/{name}")
  String getCourseIdByName(@PathVariable("name") String name);

  /**
   * Gets name by course id.
   *
   * @param courseId the course id
   * @return the name by course id
   */
  @GetMapping(value = "/academicsFeignClientController/getNameByCourseId/{courseId}")
  String getNameByCourseId(@PathVariable("courseId") String courseId);

  /**
   * Gets course list.
   *
   * @return the course list
   */
  @GetMapping(value = "/academicsFeignClientController/getCourseList")
  List<String> getCourseList();
}
