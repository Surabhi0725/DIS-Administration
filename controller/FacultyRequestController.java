package sgsits.cse.dis.administration.controller;

import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sgsits.cse.dis.administration.constants.FacultyRequestConstants;
import sgsits.cse.dis.administration.constants.RestAPI;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.dto.request.CreateFacultyRequestDto;
import sgsits.cse.dis.administration.dto.request.UpdateFacultyRequestDto;
import sgsits.cse.dis.administration.dto.response.FacultyRequestResponseDto;
import sgsits.cse.dis.administration.dto.response.ResponseMessage;
import sgsits.cse.dis.administration.entity.FacultyRequestEntity;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;
import sgsits.cse.dis.administration.mapper.FacultyRequestMapper;
import sgsits.cse.dis.administration.service.facultyrequest.FacultyRequestService;
import sgsits.cse.dis.administration.util.JwtResolverUtils;

/**
 * The type Faculty request controller.
 */
@CrossOrigin(origins = "*")
@Api(value = "methods pertaining to resource requests from faculties")
@RestController
public class FacultyRequestController {

  /**
   * The Faculty request service.
   */
  @Autowired
  private FacultyRequestService facultyRequestService;

  /**
   * The Jwt resolver.
   */
  @Autowired
  private JwtResolverUtils jwtResolverUtils;

  /**
   * The Faculty request mapper.
   */
  @Autowired
  private FacultyRequestMapper facultyRequestMapper;

  /**
   * Add faculty request response entity.
   *
   * @param facultyRequestDto the faculty request dto
   * @param request            the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_FACULTY_RESOURCE_REQUEST)
  public ResponseEntity<ResponseMessage> addFacultyRequest(
      @RequestBody CreateFacultyRequestDto facultyRequestDto,
      HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
      String name = jwtResolverUtils.getUserNameFromAuthHeader(authHeader);
      FacultyRequestEntity facultyRequest = facultyRequestMapper.toEntity(facultyRequestDto);
      facultyRequestService.addRequest(facultyRequest, id, name);
      return new ResponseEntity<>(
          new ResponseMessage(
              FacultyRequestConstants.ADD_RESOURCE_REQUEST_SUCESS
          ),
          HttpStatus.CREATED
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets faculty request.
   *
   * @param id      the id
   * @param request the request
   * @return the faculty request
   */
  @GetMapping(value = RestAPI.GET_FACULTY_RESOURCE_REQUEST)
  public ResponseEntity<FacultyRequestResponseDto> getFacultyRequest(
      @PathVariable String id,
      HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      FacultyRequestEntity facultyRequest = facultyRequestService.getRequest(id);
      FacultyRequestResponseDto facultyRequestResponseDto =
          facultyRequestMapper.toDto(facultyRequest);
      return new ResponseEntity<>(
          facultyRequestResponseDto,
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Edit faculty request response entity.
   *
   * @param requestId               the request id
   * @param updateFacultyRequestDto the update faculty request dto
   * @param request                 the request
   * @return the response entity
   */
  @PutMapping(value = RestAPI.EDIT_FACULTY_RESOURCE_REQUEST)
  public ResponseEntity<?> editFacultyRequest(
      @PathVariable("id") String requestId,
      @RequestBody UpdateFacultyRequestDto updateFacultyRequestDto,
      HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
      FacultyRequestEntity facultyRequest = facultyRequestMapper.toEntity(updateFacultyRequestDto);
      facultyRequestService.updateRequest(requestId, facultyRequest, id);
      return new ResponseEntity<>(
          new ResponseMessage(
              FacultyRequestConstants.EDIT_RESOURCE_REQUEST_SUCESS
          ),
          HttpStatus.CREATED
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets all faculty requests for id.
   *
   * @param request the request
   * @return the all faculty requests for id
   */
  @GetMapping(value = RestAPI.GET_ALL_FACULTY_REQUESTS_FOR_ID)
  public ResponseEntity<List<FacultyRequestResponseDto>> getAllFacultyRequestsForId(
      HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
      return new ResponseEntity<>(
          facultyRequestService.getUnresolvedRequestsById(id)
              .stream()
              .map(facultyRequestMapper::toDto)
              .collect(Collectors.toList()),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets all resolved faculty requests.
   *
   * @param request the request
   * @return the all resolved faculty requests
   */
  @GetMapping(value = RestAPI.GET_ALL_FACULTY_REQUESTS_RESOLVED)
  public ResponseEntity<List<FacultyRequestResponseDto>> getAllResolvedFacultyRequests(
      HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
      return new ResponseEntity<>(
          facultyRequestService.getAllResolvedRequests(id)
              .stream()
              .map(facultyRequestMapper::toDto)
              .collect(Collectors.toList()),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets all unrelsoved faculty requests.
   *
   * @param request the request
   * @return the all unrelsoved faculty requests
   */
  @GetMapping(value = RestAPI.GET_ALL_FACULTY_REQUESTS_UNRESOLVED)
  public ResponseEntity<List<FacultyRequestResponseDto>> getAllUnResolvedFacultyRequests(
      HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
      return new ResponseEntity<>(
          facultyRequestService.getAllUnresolvedRequests(id,
                  userType.equalsIgnoreCase(SecurityConstants.HEAD))
              .stream()
              .map(facultyRequestMapper::toDto)
              .collect(Collectors.toList()),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Edit to resolved response entity.
   *
   * @param requestId the request id
   * @param request   the request
   * @return the response entity
   */
  @RequestMapping(value = RestAPI.SET_FACULTY_REQUEST_RESOLVED, method = RequestMethod.PUT)
  public ResponseEntity<ResponseMessage> editToResolved(
      @PathVariable("id") String requestId,
      HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
      facultyRequestService.setResolved(requestId, id,
          userType.equalsIgnoreCase(SecurityConstants.HEAD));
      return new ResponseEntity<>(
          new ResponseMessage(
              FacultyRequestConstants.SET_STATUS_RESOLVED_SUCCESS
          ),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

}
