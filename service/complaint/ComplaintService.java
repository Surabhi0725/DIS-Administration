package sgsits.cse.dis.administration.service.complaint;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import sgsits.cse.dis.administration.dto.response.*;
import sgsits.cse.dis.administration.entity.ComplaintEntity;
import sgsits.cse.dis.administration.exception.ConflictException;

/**
 * The interface  complaint service.
 */
@Service
public interface ComplaintService {

  /**
   * Add multiple complaint list.
   *
   * @param complaintList the complaint list
   * @param userId        the user id
   * @return the list
   */
  List<ComplaintEntity> addMultipleComplaint(List<ComplaintEntity> complaintList,
      String userId) throws ConflictException;

  /**
   * Assign a user to complaint complaint entity.
   *
   * @param complaintId the complaint id
   * @param assigneeIds the assignee ids
   * @param updatedBy   the updated by
   * @return the complaint entity
   * @throws ConflictException the conflict exception
   */
  ComplaintEntity assignAUserToComplaint(
      String complaintId,
      Set<String> assigneeIds,
      String updatedBy
  ) throws ConflictException;

  /**
   * Resolve complaint complaint entity.
   *
   * @param complaintId the complaint id
   * @param resolvedBy  the resolved by
   * @param remarks     the remarks
   * @return the complaint entity
   * @throws ConflictException the conflict exception
   */
  ComplaintEntity resolveComplaint(
      String complaintId,
      String resolvedBy,
      String remarks
  ) throws ConflictException;

  /**
   * Reject complaint complaint entity.
   *
   * @param complaintId the complaint id
   * @param rejectedBy  the rejected by
   * @param remarks     the remarks
   * @return the complaint entity
   * @throws ConflictException the conflict exception
   */
  ComplaintEntity rejectComplaint(
      String complaintId,
      String rejectedBy,
      String remarks
  ) throws ConflictException;

  /**
   * Gets total complaints.
   *
   * @param complaintStatus the complaint status
   * @param complaintType   the complaint type
   * @return the total complaints
   */
  List<ComplaintResponseDto> getComplaints(
      String complaintStatus,
      String complaintType
  );

  /**
   * Gets complaints assigned to user id.
   *
   * @param userId the user id
   * @return the complaints assigned to user id
   * @throws ConflictException the conflict exception
   */
  List<ComplaintEntity> getComplaintsAssignedToUserId(
      String userId
  ) throws ConflictException;

  /**
   * Gets complaints created by user id.
   *
   * @param userId the user id
   * @return the complaints created by user id
   */
  List<ComplaintEntity> getComplaintsCreatedByUserId(
      String userId
  ) throws ConflictException;

  /**
   * Gets complaint report.
   *
   * @param userId the user id
   * @return the complaint report
   */
  ComplaintReportResponseDto getComplaintReport(String userId) throws ConflictException;

  List<ComplaintEntity>  getAllComplaints();

  List<ComplaintResponseDto> getComplaintsCreatedByUserIdAndComplaintType(String id, String complaintType);

  List<StudentDto> getStudentList();

  List<StudentDto> getStudentFullNameList();

  ComplaintCountDto getComplaintCounts(String userType, String userId);

  List<ComplaintTypePendingStateDto> getComplaintPendingStates(String userType, String userId);
}
