package sgsits.cse.dis.administration.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sgsits.cse.dis.administration.entity.LibraryThesisRecordsEntity;

/**
 * <h1>LibrarayThesisRecordsRepository</h1> interface.
 * this repository contains Jpafunciton to perform crud operation.
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2019
 */
@Repository
public interface LibraryThesisRecordsRepository
    extends CrudRepository<LibraryThesisRecordsEntity, String> {

  /**
   * Find by title containing ignore case list.
   *
   * @param title the title
   * @return the list
   */
  List<LibraryThesisRecordsEntity> findByTitleContainingIgnoreCase(String title);

  /**
   * Find by submitted by containing ignore case list.
   *
   * @param submittedBy the submitted by
   * @return the list
   */
  List<LibraryThesisRecordsEntity> findBySubmittedByContainingIgnoreCase(String submittedBy);

  /**
   * Find by guided by containing ignore case list.
   *
   * @param guidedBy the guided by
   * @return the list
   */
  List<LibraryThesisRecordsEntity> findByGuidedByContainingIgnoreCase(String guidedBy);

  /**
   * Find by course containing ignore case list.
   *
   * @param course the course
   * @return the list
   */
  List<LibraryThesisRecordsEntity> findByCourseContainingIgnoreCase(String course);

}
