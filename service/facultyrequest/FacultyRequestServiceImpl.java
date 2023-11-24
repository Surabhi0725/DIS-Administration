package sgsits.cse.dis.administration.service.facultyrequest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.entity.FacultyRequestEntity;
import sgsits.cse.dis.administration.enums.FacultyRequestStatus;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;
import sgsits.cse.dis.administration.exception.ResourceRequestNotFoundException;
import sgsits.cse.dis.administration.feignClient.UserClient;
import sgsits.cse.dis.administration.repo.FacultyRequestRepo;
import sgsits.cse.dis.administration.service.facultyrequest.FacultyRequestService;
import sgsits.cse.dis.administration.util.GeneralUtils;

/**
 * The type Faculty request service.
 */
@Service
public class FacultyRequestServiceImpl implements FacultyRequestService {

  /**
   * The User client.
   */
  @Autowired
  private UserClient userClient;
  /**
   * The Faculty request repo.
   */
  @Autowired
  private FacultyRequestRepo facultyRequestRepo;

  /**
   * The General utils.
   */
  @Autowired
  private GeneralUtils generalUtils;

  @Override
  public void addRequest(FacultyRequestEntity facultyRequest, String requestById,
      String requestByUserName) {
    facultyRequest.setCreatedBy(requestById);
    facultyRequest.setLastModifiedBy(requestById);
    facultyRequest.setUsername(requestByUserName);
    facultyRequest.setAssignedPersonId(
        userClient.getUserId(SecurityConstants.DEFAULT_FACULTY_REQUEST_HANDLER));
    facultyRequest.setAssignedPersonUsername(SecurityConstants.DEFAULT_FACULTY_REQUEST_HANDLER);
    facultyRequest.setStatus(FacultyRequestStatus.IN_PROGRESS);
    facultyRequestRepo.save(facultyRequest);
  }

  @Override
  public FacultyRequestEntity getRequest(String requestId) {
    return facultyRequestRepo.findById(requestId).orElseThrow(
        () -> new ResourceRequestNotFoundException("Resource not found")
    );
  }

  @Override
  public void updateRequest(
      String requestId,
      FacultyRequestEntity facultyRequest,
      String requestById
  ) {
    if (requestById.equals(
        userClient.getUserId(SecurityConstants.DEFAULT_FACULTY_REQUEST_HANDLER))) {
      FacultyRequestEntity existingRequest = getRequest(requestId);
      facultyRequest.setAssignedPersonUsername(userClient.getUserIdByUserName(facultyRequest.getAssignedPersonId()));
      facultyRequest.setLastModifiedBy(requestById);
      generalUtils.copyNotNullProperties(facultyRequest, existingRequest);
      facultyRequestRepo.save(existingRequest);
    } else {
      throw new ResourceRequestNotAccessibleException("You cannot access this resource!");
    }
  }

  @Override
  public List<FacultyRequestEntity> getUnresolvedRequestsById(String userId) {
    return facultyRequestRepo.findByCreatedByAndStatusNotOrderByCreatedDateDesc(userId,
        FacultyRequestStatus.RESOLVED);
  }

  @Override
  public List<FacultyRequestEntity> getAllResolvedRequests(String userId) {
    String defaultId = userClient.getUserId(SecurityConstants.DEFAULT_FACULTY_REQUEST_HANDLER);
    if (defaultId.equals(userId)) {
      return facultyRequestRepo.findByStatus(FacultyRequestStatus.RESOLVED);
    } else {
      throw new ResourceRequestNotAccessibleException("You cannot access this resource!");
    }
  }

  @Override
  public List<FacultyRequestEntity> getAllUnresolvedRequests(String userId, Boolean isHead) {
    String defaultId = userClient.getUserId(SecurityConstants.DEFAULT_FACULTY_REQUEST_HANDLER);
    if (defaultId.equals(userId) || isHead) {
      return facultyRequestRepo.findByStatusNotOrderByCreatedDateAsc(FacultyRequestStatus.RESOLVED);
    } else {
      throw new ResourceRequestNotAccessibleException("You cannot access this resource!");
    }
  }

  @Override
  public void setResolved(String requestId, String userId, Boolean isHead) {
    String defaultId = userClient.getUserId(SecurityConstants.DEFAULT_FACULTY_REQUEST_HANDLER);
    if (defaultId.equals(userId) || isHead) {
      FacultyRequestEntity existingRequest = getRequest(requestId);
      existingRequest.setStatus(FacultyRequestStatus.RESOLVED);
      existingRequest.setLastModifiedBy(userId);
      facultyRequestRepo.save(existingRequest);
    } else {
      throw new ResourceRequestNotAccessibleException("You cannot access this resource!");
    }
  }
}
