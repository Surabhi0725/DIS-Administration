package sgsits.cse.dis.administration.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import sgsits.cse.dis.administration.entity.LibraryIssueEntity;

/**
 * <h1>LibraryIssuesRepository</h1> interface.
 * this repository contains Jpafunciton to perform crud operation.
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2019
 */
public interface LibraryIssueRepository
    extends CrudRepository<LibraryIssueEntity, String> {


  /**
   * Find by username ignore case and is returned list.
   *
   * @param username   the username
   * @param isReturned the is returned
   * @return the list
   */
  List<LibraryIssueEntity> findByUsernameIgnoreCaseAndIsReturned(String username,
      Boolean isReturned);

  /**
   * Find by book id list.
   *
   * @param bookId     the book id
   * @param isReturned the is returned
   * @return the list
   */
  List<LibraryIssueEntity> findByBookIdAndIsReturned(String bookId, Boolean isReturned);

  /**
   * Find by thesis id list.
   *
   * @param thesisId   the thesis id
   * @param isReturned the is returned
   * @return the list
   */
  List<LibraryIssueEntity> findByThesisIdAndIsReturned(String thesisId, Boolean isReturned);
}
