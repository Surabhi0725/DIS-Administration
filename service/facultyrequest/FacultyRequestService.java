package sgsits.cse.dis.administration.service.facultyrequest;

import java.util.List;
import sgsits.cse.dis.administration.entity.FacultyRequestEntity;

/**
 * The interface Faculty request service.
 */
public interface FacultyRequestService {

  /**
   * Add request faculty request entity.
   *
   * @param facultyRequest    the faculty request
   * @param requestById       the request by id
   * @param requestByUserName the request by user name
   */
  void addRequest(
      FacultyRequestEntity facultyRequest,
      String requestById,
      String requestByUserName
  );

  /**
   * Gets request.
   *
   * @param requestId the request id
   * @return the request
   */
  FacultyRequestEntity getRequest(String requestId);

  /**
   * Update request.
   *
   * @param requestId      the request id
   * @param facultyRequest the faculty request
   * @param requestById    the request by id
   */
  void updateRequest(
      String requestId,
      FacultyRequestEntity facultyRequest,
      String requestById
  );

  /**
   * Gets unresolved requests by id.
   *
   * @param userId the user id
   * @return the unresolved requests by id
   */
  List<FacultyRequestEntity> getUnresolvedRequestsById(String userId);

  /**
   * Gets all resolved requests.
   *
   * @param userId the user id
   * @return the all resolved requests
   */
  List<FacultyRequestEntity> getAllResolvedRequests(String userId);

  /**
   * Gets all unresolved requests.
   *
   * @param userId the user id
   * @param isHead the is head
   * @return the all unresolved requests
   */
  List<FacultyRequestEntity> getAllUnresolvedRequests(String userId, Boolean isHead);

  /**
   * Sets resolved.
   *
   * @param requestId the request id
   * @param userId    the user id
   * @param isHead    the is head
   */
  void setResolved(String requestId, String userId, Boolean isHead);
}
