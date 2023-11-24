package sgsits.cse.dis.administration.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import sgsits.cse.dis.administration.entity.FacultyRequestEntity;
import sgsits.cse.dis.administration.enums.FacultyRequestStatus;

/**
 * The interface Faculty request repo.
 */
public interface FacultyRequestRepo extends CrudRepository<FacultyRequestEntity, String> {

  Optional<FacultyRequestEntity> findById(String id);

  /**
   * Find by created by and status not order by created date desc list.
   *
   * @param id     the id
   * @param status the status
   * @return the list
   */
  List<FacultyRequestEntity> findByCreatedByAndStatusNotOrderByCreatedDateDesc(String id,
      FacultyRequestStatus status);

  /**
   * Find by status list.
   *
   * @param status the status
   * @return the list
   */
  List<FacultyRequestEntity> findByStatus(FacultyRequestStatus status);

  /**
   * Find by status not order by created date asc list.
   *
   * @param status the status
   * @return the list
   */
  List<FacultyRequestEntity> findByStatusNotOrderByCreatedDateAsc(FacultyRequestStatus status);
}
