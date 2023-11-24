package sgsits.cse.dis.administration.service.librarybook;

import java.util.List;
import sgsits.cse.dis.administration.entity.LibraryBookRecordsEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;


/**
 * The interface Library book service.
 */
public interface LibraryBookService {

  /**
   * add a new book into the system.
   *
   * @param libraryBookRecordsEntity the library book records entity
   * @param createdBy                the created by
   * @return message string
   * @throws ConflictException the conflict exception
   */
  String addBook(LibraryBookRecordsEntity libraryBookRecordsEntity, String createdBy)
      throws ConflictException;

  /**
   * List all book present in the system.
   *
   * @return LibraryBookRecordsEntity List
   */
  List<LibraryBookRecordsEntity> getAllBooks();

  /**
   * List all book by given "title" present in the system.
   *
   * @param title the title
   * @return LibraryBookRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryBookRecordsEntity> getBookByTitle(String title) throws EventDoesNotExistException;

  /**
   * List all book by given "bookId" present in the system.
   *
   * @param bookId the book id
   * @return LibraryBookRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  LibraryBookRecordsEntity getBookByBookId(String bookId) throws EventDoesNotExistException;

  /**
   * List all book by given "authorName" present in the system.
   *
   * @param authorName the author name
   * @return LibraryBookRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   */
  List<LibraryBookRecordsEntity> getBookByAuthorName(String authorName)
      throws EventDoesNotExistException;

  /**
   * update book currently present in the system.
   *
   * @param libraryBookRecordsEntity the library book records entity
   * @param bookId                   the book id
   * @param updatedBy                the updated by
   * @return LibraryBookRecordsEntity List
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  void updateBook(LibraryBookRecordsEntity libraryBookRecordsEntity, String bookId,
      String updatedBy)
      throws EventDoesNotExistException, ConflictException;

  /**
   * delete a book currently present in the system.
   *
   * @param bookId the book id
   * @return void
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  void deleteBook(String bookId) throws EventDoesNotExistException, ConflictException;
}
