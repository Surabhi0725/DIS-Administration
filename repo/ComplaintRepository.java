package sgsits.cse.dis.administration.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sgsits.cse.dis.administration.entity.ComplaintEntity;
import sgsits.cse.dis.administration.enums.ComplaintStatus;
import sgsits.cse.dis.administration.enums.ComplaintType;


/**
 * The interface  complaint repository.
 */
@Repository
public interface ComplaintRepository
    extends CrudRepository<ComplaintEntity, String> {

  /**
   * Find by complaint status and complaint type list.
   *
   * @param complaintStatus the complaint status
   * @param complaintType   the complaint type
   * @return the list
   */
  List<ComplaintEntity> findByComplaintStatusAndComplaintType(
      ComplaintStatus complaintStatus,
      ComplaintType complaintType
  );

  /**
   * Find by complaint status list.
   *
   * @param complaintStatus the complaint status
   * @return the list
   */
  List<ComplaintEntity> findByComplaintStatus(
      ComplaintStatus complaintStatus
  );

  /**
   * Find by complaint type list.
   *
   * @param complaintType the complaint type
   * @return the list
   */
  List<ComplaintEntity> findByComplaintType(
      ComplaintType complaintType
  );

  /**
   * Find by location in list.
   *
   * @param location the location
   * @return the list
   */
  List<ComplaintEntity> findByLocationIn(List<String> location);

  /**
   * Find by assigned user ids ignore case containing list.
   *
   * @param assigneeId the assignee id
   * @return the list
   */
  List<ComplaintEntity> findByAssignedUserIdsIgnoreCaseContaining(String assigneeId);

  /**
   * Find by created by list.
   *
   * @param createdBy the created by
   * @return the list
   */
  List<ComplaintEntity> findByCreatedBy(String createdBy);

  List<ComplaintEntity> findByCreatedByAndComplaintType(String createdBy, ComplaintType complaintType);


  long countByComplaintStatus(ComplaintStatus complaintStatus);

  long countByCreatedBy(String userId);

  long countByCreatedByAndComplaintStatus(String userId, ComplaintStatus complaintStatus);

  List<ComplaintEntity> findByCreatedByAndComplaintStatus(String userId, ComplaintStatus pending);
}