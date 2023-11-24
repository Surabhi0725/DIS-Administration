package sgsits.cse.dis.administration.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sgsits.cse.dis.administration.entity.LibraryBookRecordsEntity;

/**
 * <h1>BookRecordsRepository</h1> interface.
 * this repository contains Jpafunciton to perform crud operation.
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2019
 */
@Repository
public interface LibraryBookRecordsRepository
    extends CrudRepository<LibraryBookRecordsEntity, String> {

  /**
   * Find by title containing ignore case list.
   *
   * @param title the title
   * @return the list
   */
  List<LibraryBookRecordsEntity> findByTitleContainingIgnoreCase(String title);

  /**
   * Find by author name containing ignore case list.
   *
   * @param authorName the author name
   * @return the list
   */
  List<LibraryBookRecordsEntity> findByAuthorNameContainingIgnoreCase(String authorName);
}
