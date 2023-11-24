package sgsits.cse.dis.administration.service.librarysubjectcategory;

import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import sgsits.cse.dis.administration.entity.LibrarySubjectCategoryEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;

/**
 * <h1><b>LibraryService</b> interface.</h1>
 * <p>This interface lists all the library services which can be implemented by class extending it.
 *
 * @author Arjit Mishra.
 * @version 1.0.
 * @throws ConflictException
 * @throws EventDoesNotExistException
 * @throws DataIntegrityViolationException
 * @throws MethodArgumentNotValidException
 * @see DataIntegrityViolationException
 * @see MethodArgumentNotValidException
 * @since 2 -DEC-2019.
 */
public interface LibrarySubjectCategoryService {

  /**
   * Gets subject category acronym list.
   *
   * @return the subject category acronym list
   */
  List<LibrarySubjectCategoryEntity> getSubjectCategoryAcronymList();

  /**
   * Add new subject category.
   *
   * @param librarySubjectCategoryEntity the library subject category entity
   * @param createdBy                    the created by
   * @throws ConflictException the conflict exception
   */
  void addNewSubjectCategory(LibrarySubjectCategoryEntity librarySubjectCategoryEntity,
      String createdBy)
      throws ConflictException;

  /**
   * Delete subject category.
   *
   * @param subjectCategoryId the subject category id
   * @throws EventDoesNotExistException the event does not exist exception
   */
  void deleteSubjectCategory(String subjectCategoryId) throws EventDoesNotExistException;

  /**
   * Update subject category.
   *
   * @param librarySubjectCategoryEntity the library subject category entity
   * @param subjectCategoryId            the subject category id
   * @param updatedBy                    the updated by
   * @throws EventDoesNotExistException the event does not exist exception
   */
  void updateSubjectCategory(LibrarySubjectCategoryEntity librarySubjectCategoryEntity,
      String subjectCategoryId, String updatedBy)
      throws EventDoesNotExistException;

  /**
   * Gets subject category.
   *
   * @param subjectCategoryId the subject category id
   * @return the subject category
   * @throws EventDoesNotExistException the event does not exist exception
   */
  LibrarySubjectCategoryEntity getSubjectCategory(String subjectCategoryId)
      throws EventDoesNotExistException;
}
