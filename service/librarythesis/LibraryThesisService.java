package sgsits.cse.dis.administration.service.librarythesis;

import java.util.List;
import sgsits.cse.dis.administration.entity.LibraryThesisRecordsEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;

/**
 * The interface Library thesis service.
 */
public interface LibraryThesisService {

  /**
   * add new thesis in the system.
   *
   * @param libraryThesisRecords the library thesis records
   * @param createdBy            the created by
   * @return thesisId long
   * @throws ConflictException the conflict exception
   */
  String addThesis(LibraryThesisRecordsEntity libraryThesisRecords, String createdBy)
      throws ConflictException;

  /**
   * Get list of all thesis in the system.
   *
   * @return LibraryThesisRecordsEntity List
   */
  List<LibraryThesisRecordsEntity> getAllThesis();

  /**
   * Get list of all thesis by "title" in the system.
   *
   * @param title the title
   * @return LibraryThesisRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryThesisRecordsEntity> getThesisByTitle(String title) throws EventDoesNotExistException;

  /**
   * Get list of all thesis by "submittedBy" in the system.
   *
   * @param submittedBy the submitted by
   * @return LibraryThesisRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryThesisRecordsEntity> getThesisBySubmittedBy(String submittedBy)
      throws EventDoesNotExistException;

  /**
   * Get list of all thesis by "guidedBy" in the system.
   *
   * @param guidedBy the guided by
   * @return LibraryThesisRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryThesisRecordsEntity> getThesisByGuidedBy(String guidedBy)
      throws EventDoesNotExistException;

  /**
   * Get list of all thesis by "thesisId" in the system.
   *
   * @param thesisId the thesis id
   * @return LibraryThesisRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  LibraryThesisRecordsEntity getThesisByThesisId(String thesisId)
      throws EventDoesNotExistException;

  /**
   * Get list of all thesis by "course" in the system.
   *
   * @param course the course
   * @return LibraryThesisRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryThesisRecordsEntity> getThesisByCourse(String course)
      throws EventDoesNotExistException;

  /**
   * update a thesis in the system.
   *
   * @param libraryThesisRecords the library thesis records
   * @param thesisId             the thesis id
   * @param updatedBy            the updated by
   * @return void
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  void updateThesis(LibraryThesisRecordsEntity libraryThesisRecords, String thesisId,
      String updatedBy)
      throws EventDoesNotExistException, ConflictException;

  /**
   * delete a thesis in the system.
   *
   * @param thesisId the thesis id
   * @return void
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  void deleteThesis(String thesisId) throws EventDoesNotExistException, ConflictException;
}
