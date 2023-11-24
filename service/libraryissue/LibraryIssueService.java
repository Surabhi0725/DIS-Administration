package sgsits.cse.dis.administration.service.libraryissue;

import java.text.ParseException;
import java.util.List;
import sgsits.cse.dis.administration.dto.response.IssuedInformationResponse;
import sgsits.cse.dis.administration.entity.LibraryIssueEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;

/**
 * The interface Library issue service.
 */
public interface LibraryIssueService {

  /**
   * Get previous issue  of a particular user by "username".
   *
   * @param username the username
   * @return LibraryIssueEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryIssueEntity> getPreviousIssuesByUsername(String username)
      throws EventDoesNotExistException;

  /**
   * Get previous issue  of a particular book by "bookId".
   *
   * @param bookId the book id
   * @return LibraryIssueEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryIssueEntity> getPreviousIssuesByBookId(String bookId)
      throws EventDoesNotExistException;

  /**
   * Get previous issue  of a particular thesis by "thesisId".
   *
   * @param thesisId the thesis id
   * @return LibraryIssueEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryIssueEntity> getPreviousIssuesByThesisId(String thesisId)
      throws EventDoesNotExistException;


  /**
   * Issue a book or thesis.
   *
   * @param libraryIssueEntity the library issue entity
   * @param createdBy          the created by
   * @return message string
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  String issue(LibraryIssueEntity libraryIssueEntity, String createdBy)
      throws EventDoesNotExistException, ConflictException;

  /**
   * Get no of issues by the "username".
   *
   * @param username the username
   * @return noOfIssues no of issues
   */
  List<LibraryIssueEntity> getCurrentIssuesByUsername(String username);

  /**
   * Return a book.
   *
   * @param bookId     the book id
   * @param modifiedBy the modified by
   * @return message. string
   * @throws ParseException             the parse exception
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  String returnBook(String bookId, String modifiedBy)
      throws ParseException, EventDoesNotExistException, ConflictException;

  /**
   * Get current issued information of a book by the bookId.
   *
   * @param bookId the book id
   * @return IssuedInformationResponse. issued book info
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ParseException             the parse exception
   */
  IssuedInformationResponse getIssuedBookInfo(String bookId)
      throws EventDoesNotExistException, ParseException;

  /**
   * Return a thesis.
   *
   * @param thesisId   the thesis id
   * @param modifiedBy the modified by
   * @return message. string
   * @throws ParseException             the parse exception
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  String returnThesis(String thesisId, String modifiedBy)
      throws ParseException, EventDoesNotExistException, ConflictException;

  /**
   * Get current issued information of a thesis by the thesisId.
   *
   * @param thesisId the thesis id
   * @return IssuedInformationResponse. issued thesis info
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ParseException             the parse exception
   */
  IssuedInformationResponse getIssuedThesisInfo(String thesisId)
      throws EventDoesNotExistException, ParseException;

}
