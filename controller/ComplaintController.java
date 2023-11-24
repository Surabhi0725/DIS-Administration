package sgsits.cse.dis.administration.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;
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
import org.springframework.web.bind.annotation.RestController;
import sgsits.cse.dis.administration.constants.ComplaintConstants;
import sgsits.cse.dis.administration.constants.RestAPI;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.dto.request.CWNComplaintDto;
import sgsits.cse.dis.administration.dto.request.CleanlinessComplaintDto;
import sgsits.cse.dis.administration.dto.request.ECCWComplaintDto;
import sgsits.cse.dis.administration.dto.request.EMRComplaintDto;
import sgsits.cse.dis.administration.dto.request.FacultyComplaintDto;
import sgsits.cse.dis.administration.dto.request.LEComplaintDto;
import sgsits.cse.dis.administration.dto.request.OtherComplaintDto;
import sgsits.cse.dis.administration.dto.request.StudentComplaintDto;
import sgsits.cse.dis.administration.dto.request.TelephoneComplaintDto;
import sgsits.cse.dis.administration.dto.response.*;
import sgsits.cse.dis.administration.entity.ComplaintEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;
import sgsits.cse.dis.administration.feignClient.InfrastructureClient;
import sgsits.cse.dis.administration.mapper.ComplaintMapper;
import sgsits.cse.dis.administration.service.complaint.ComplaintService;
import sgsits.cse.dis.administration.util.JwtResolverUtils;

/**
 * The type Complaint controller.
 */
@CrossOrigin(origins = "*")
@RestController
public class ComplaintController {

  /**
   * The Jwt resolver.
   */
  @Autowired
  private JwtResolverUtils jwtResolver;

  /**
   * The Complaint service.
   */
  @Autowired
  private ComplaintService complaintService;

  /**
   * The Complaint mapper.
   */
  @Autowired
  private ComplaintMapper complaintMapper;

  /**
   * The Infrastructure client.
   */
  @Autowired
  private InfrastructureClient infrastructureClient;

  /**
   * Gets complaints based on type and status.
   *
   * @param request         the request
   * @param complaintStatus the complaint status
   * @param complaintType   the complaint type
   * @return the complaints based on type and status
   */
  @GetMapping(value = RestAPI.GET_COMPLAINTS)
  public ResponseEntity<List<ComplaintResponseDto>> getComplaintsBasedOnTypeAndStatus(
      HttpServletRequest request,
      @PathVariable(value = "complaintStatus") String complaintStatus,
      @PathVariable(value = "complaintType") String complaintType
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      return new ResponseEntity<>(
          complaintService
              .getComplaints(complaintStatus, complaintType)
          , HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  @GetMapping(value = RestAPI.GET_COMPLAINT_COUNT)
  public ResponseEntity<ComplaintCountDto> getComplaintCounts(
          HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    String userId = jwtResolver.getIdFromAuthHeader(authHeader);
      return new ResponseEntity<>(
              complaintService
                      .getComplaintCounts(userType, userId)
              , HttpStatus.OK);
  }

  @GetMapping(value = RestAPI.GET_COMPLAINT_PENDING_STATES_)
  public ResponseEntity<List<ComplaintTypePendingStateDto>> getComplaintPendingStates(
          HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    String userId = jwtResolver.getIdFromAuthHeader(authHeader);
    return new ResponseEntity<>(
            complaintService
                    .getComplaintPendingStates(userType, userId)
            , HttpStatus.OK);
  }
  @GetMapping(value = RestAPI.GET_ALL_COMPLAINTS)
  public ResponseEntity<List<ComplaintResponseDto>> getAllComplaints(
          HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (userType.equalsIgnoreCase(SecurityConstants.HEAD)) {
      return new ResponseEntity<>(
              complaintService
                      .getAllComplaints()
                      .stream()
                      .map(complaintMapper::toDto)
                      .collect(Collectors.toList())
              , HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets complaints assigned to user id.
   *
   * @param request the request
   * @return the complaints assigned to user id
   * @throws ConflictException the conflict exception
   */
  @GetMapping(value = RestAPI.GET_COMPLAINTS_BY_USER_ASSIGNED_TO)
  public ResponseEntity<List<ComplaintResponseDto>> getComplaintsAssignedToUserId(
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    return new ResponseEntity<>(
        complaintService
            .getComplaintsAssignedToUserId(id)
            .stream()
            .map(complaintMapper::toDto)
            .collect(Collectors.toList())
        , HttpStatus.OK);
  }

  @GetMapping(value = RestAPI.GET_STUDENT_FULL_NAME_LIST)
  public ResponseEntity<List<StudentDto>> getStudentFullNameList(
          HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    return new ResponseEntity<>(
            complaintService
                    .getStudentFullNameList()
            , HttpStatus.OK);
  }

  /**
   * Gets complaints created by user id.
   *
   * @param request the request
   * @return the complaints created by user id
   * @throws ConflictException the conflict exception
   */
  @GetMapping(value = RestAPI.GET_COMPLAINTS_CREATED_BY_USER)
  public ResponseEntity<List<ComplaintResponseDto>> getComplaintsCreatedByUserId(
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    return new ResponseEntity<>(
        complaintService
            .getComplaintsCreatedByUserId(id)
            .stream()
            .map(complaintMapper::toDto)
            .collect(Collectors.toList())
        , HttpStatus.OK);
  }

  /**
   * Gets complaints created by user id and complaintType.
   *
   * @param request the request
   * @return the complaints created by user id and complaintType
   * @throws ConflictException the conflict exception
   */
  @GetMapping(value = RestAPI.GET_COMPLAINTS_CREATED_BY_USER_TYPE)
  public ResponseEntity<List<ComplaintResponseDto>> getComplaintsCreatedByUserIdAndComplaintType(
          @PathVariable("complaintType") String complaintType,
          HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    return new ResponseEntity<>(
            complaintService
                    .getComplaintsCreatedByUserIdAndComplaintType(id, complaintType)
            , HttpStatus.OK);
  }

  /**
   * Gets student List.
   *
   * @param request the request
   * @return the complaints created by user id and complaintType
   * @throws ConflictException the conflict exception
   */
  @GetMapping(value = RestAPI.GET_STUDENT_LIST)
  public ResponseEntity<List<StudentDto>> getStudentList(
          HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    return new ResponseEntity<>(
              complaintService.getStudentList()
            , HttpStatus.OK);
  }

  /**
   * Gets complaint report.
   *
   * @param request the request
   * @return the complaint report
   * @throws ConflictException the conflict exception
   */
  @GetMapping(value = RestAPI.GET_COMPLAINTS_BY_USER_ASSIGNED_TO_COUNT)
  public ResponseEntity<ComplaintReportResponseDto> getComplaintReport(
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    return new ResponseEntity<>(complaintService.getComplaintReport(id), HttpStatus.OK);
  }

  /**
   * Add cleanliness complaint response entity.
   *
   * @param cleanlinessComplaintDto the cleanliness complaint dto
   * @param request                 the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_CLEANLINESS_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addCleanlinessComplaint(
      @RequestBody CleanlinessComplaintDto cleanlinessComplaintDto,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    ComplaintEntity complaintEntity = complaintMapper.toEntity(cleanlinessComplaintDto);
    complaintService.addMultipleComplaint(Collections.singletonList(complaintEntity), id);
    return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
        HttpStatus.OK);
  }

  /**
   * Assign a user to complaint response entity.
   *
   * @param complaintId the complaint id
   * @param assigneeIds the assignee ids
   * @param request     the request
   * @return the response entity
   * @throws ConflictException the conflict exception
   */
  @PutMapping(value = RestAPI.ADD_ASSIGNEES_TO_COMPLAINT)
  public ResponseEntity<ResponseMessage> assignAUserToComplaint(
      @PathVariable("complaintId") String complaintId,
      @RequestBody Set<String> assigneeIds,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      complaintService.assignAUserToComplaint(complaintId, assigneeIds, id);
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Resolve complaint response entity.
   *
   * @param complaintId    the complaint id
   * @param closingRemarks the closing remarks
   * @param request        the request
   * @return the response entity
   * @throws ConflictException the conflict exception
   */
  @PutMapping(value = RestAPI.RESOLVE_COMPLAINT)
  public ResponseEntity<ResponseMessage> resolveComplaint(
      @PathVariable("complaintId") String complaintId,
      @RequestBody String closingRemarks,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      complaintService.resolveComplaint(
          complaintId,
          id,
          closingRemarks
      );
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Reject complaint response entity.
   *
   * @param complaintId    the complaint id
   * @param closingRemarks the closing remarks
   * @param request        the request
   * @return the response entity
   * @throws ConflictException the conflict exception
   */
  @PutMapping(value = RestAPI.REJECT_COMPLAINT)
  public ResponseEntity<ResponseMessage> rejectComplaint(
      @PathVariable("complaintId") String complaintId,
      @RequestBody String closingRemarks,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      complaintService.rejectComplaint(
          complaintId,
          id,
          closingRemarks
      );
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Add cwn complaint response entity.
   *
   * @param cwnComplaints the cwn complaints
   * @param request       the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_CWN_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addCWNComplaint(
      @RequestBody List<CWNComplaintDto> cwnComplaints,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      List<ComplaintEntity> complaintEntities =
          cwnComplaints.stream().map(complaintMapper::toEntity).collect(Collectors.toList());
      complaintService.addMultipleComplaint(complaintEntities, id);
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Add eccw complaint response entity.
   *
   * @param eccwComplaint the eccw complaint
   * @param request       the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_ECCW_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addECCWComplaint(
      @RequestBody List<ECCWComplaintDto> eccwComplaint,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      List<ComplaintEntity> complaintEntities =
          eccwComplaint.stream().map(complaintMapper::toEntity).collect(Collectors.toList());
      complaintService.addMultipleComplaint(complaintEntities, id);
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Add emrs complaint response entity.
   *
   * @param emrComplaints the emr complaints
   * @param request       the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_EMRS_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addEMRSComplaint(
      @RequestBody List<EMRComplaintDto> emrComplaints,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      List<ComplaintEntity> complaintEntities =
          emrComplaints.stream().map(complaintMapper::toEntity).collect(Collectors.toList());
      complaintService.addMultipleComplaint(complaintEntities, id);
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Add le complaint response entity.
   *
   * @param leComplaintDto the le complaint dto
   * @param request        the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_LE_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addLEComplaint(
      @RequestBody LEComplaintDto leComplaintDto,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    ComplaintEntity complaintEntity = complaintMapper.toEntity(leComplaintDto);
    complaintService.addMultipleComplaint(Collections.singletonList(complaintEntity), id);
    return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
        HttpStatus.OK);
  }

  /**
   * Add other complaint response entity.
   *
   * @param otherComplaintDto the other complaint dto
   * @param request           the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_OTHER_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addOtherComplaint(
      @RequestBody OtherComplaintDto otherComplaintDto,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    ComplaintEntity complaintEntity = complaintMapper.toEntity(otherComplaintDto);
    complaintService.addMultipleComplaint(Collections.singletonList(complaintEntity), id);
    return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
        HttpStatus.OK);
  }

  /**
   * Add student complaint response entity.
   *
   * @param studentComplaintDto the student complaint dto
   * @param request             the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_STUDENT_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addStudentComplaint(
      @RequestBody StudentComplaintDto studentComplaintDto,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (userType.equalsIgnoreCase(SecurityConstants.FACULTY)) {
      ComplaintEntity complaintEntity = complaintMapper.toEntity(studentComplaintDto);
      complaintService.addMultipleComplaint(Collections.singletonList(complaintEntity), id);
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Add telephone complaint response entity.
   *
   * @param telephoneComplaintDto the telephone complaint Dto
   * @param request               the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_TELEPHONE_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addTelephoneComplaint(
      @RequestBody TelephoneComplaintDto telephoneComplaintDto,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      ComplaintEntity complaintEntity = complaintMapper.toEntity(telephoneComplaintDto);
      complaintService.addMultipleComplaint(Collections.singletonList(complaintEntity), id);
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Add faculty complaint response entity.
   *
   * @param facultyComplaintDto the faculty complaint dto
   * @param request             the request
   * @return the response entity
   */
  @PostMapping(value = RestAPI.ADD_FACULTY_COMPLAINTS)
  public ResponseEntity<ResponseMessage> addFacultyComplaint(
      @RequestBody FacultyComplaintDto facultyComplaintDto,
      HttpServletRequest request
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolver.getIdFromAuthHeader(authHeader);
    String userType = jwtResolver.getUserTypeFromAuthHeader(authHeader);
    if (userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      ComplaintEntity complaintEntity = complaintMapper.toEntity(facultyComplaintDto);
      complaintService.addMultipleComplaint(Collections.singletonList(complaintEntity), id);
      return new ResponseEntity<>(new ResponseMessage(ComplaintConstants.ADD_COMPLAINT_SUCCESS),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }
}
