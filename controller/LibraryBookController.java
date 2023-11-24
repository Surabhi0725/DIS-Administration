package sgsits.cse.dis.administration.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sgsits.cse.dis.administration.constants.RestAPI;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.dto.request.CreateUpdateBookDto;
import sgsits.cse.dis.administration.dto.response.LibraryBookRecordsResponseDto;
import sgsits.cse.dis.administration.dto.response.ResponseMessage;
import sgsits.cse.dis.administration.entity.LibraryBookRecordsEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;
import sgsits.cse.dis.administration.mapper.BookRecordMapper;
import sgsits.cse.dis.administration.service.librarybook.LibraryBookService;
import sgsits.cse.dis.administration.util.JwtResolverUtils;

/**
 * The type Library book controller.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/libraryBook")
public class LibraryBookController {

  /**
   * The Library book service.
   */
  @Autowired
  private LibraryBookService libraryBookService;

  /**
   * The Jwt resolver utils.
   */
  @Autowired
  private JwtResolverUtils jwtResolverUtils;

  /**
   * The Book record mapper.
   */
  @Autowired
  private BookRecordMapper bookRecordMapper;

  /**
   * Add book response entity.
   *
   * @param request     the request
   * @param addBookForm the add book form
   * @return the response entity
   * @throws ConflictException the conflict exception
   */
  @PostMapping(path = RestAPI.ADD_BOOK)
  public ResponseEntity<ResponseMessage> addBook(
      HttpServletRequest request,
      @RequestBody CreateUpdateBookDto addBookForm
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      LibraryBookRecordsEntity libraryBookRecordsEntity = bookRecordMapper.toEntity(addBookForm);
      libraryBookService.addBook(libraryBookRecordsEntity, id);
      return new ResponseEntity<>(
          new ResponseMessage("Book added successfully."),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets all books.
   *
   * @return the all books
   */
  @GetMapping(path = RestAPI.GET_ALL_BOOKS)
  public ResponseEntity<List<LibraryBookRecordsResponseDto>> getAllBooks() {
    return new ResponseEntity<>(
        libraryBookService.getAllBooks()
            .stream()
            .map(bookRecordMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Gets book by title.
   *
   * @param title the title
   * @return the book by title
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_BOOK_BY_TITLE)
  public ResponseEntity<List<LibraryBookRecordsResponseDto>> getBookByTitle(
      @PathVariable("title") String title) throws EventDoesNotExistException {
    return new ResponseEntity<>(
        libraryBookService.getBookByTitle(title)
            .stream()
            .map(bookRecordMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Gets book by book id.
   *
   * @param bookId the book id
   * @return the book by book id
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_BOOK_BY_BOOK_ID)
  public ResponseEntity<LibraryBookRecordsResponseDto> getBookByBookId(
      @PathVariable("bookId") String bookId
  ) throws EventDoesNotExistException {
    LibraryBookRecordsEntity libraryBookRecordsEntity = libraryBookService.getBookByBookId(bookId);
    LibraryBookRecordsResponseDto libraryBookRecordsResponseDto =
        bookRecordMapper.toDto(libraryBookRecordsEntity);
    return new ResponseEntity<>(libraryBookRecordsResponseDto, HttpStatus.OK);
  }


  /**
   * Gets book by author name.
   *
   * @param authorName the author name
   * @return the book by author name
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_BOOK_BY_AUTHOR_NAME)
  public ResponseEntity<List<LibraryBookRecordsResponseDto>> getBookByAuthorName(
      @PathVariable("authorName") String authorName
  ) throws EventDoesNotExistException {
    return new ResponseEntity<>(
        libraryBookService.getBookByAuthorName(authorName)
            .stream()
            .map(bookRecordMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Update book response entity.
   *
   * @param request             the request
   * @param bookId              the book id
   * @param createUpdateBookDto the create update book dto
   * @return the response entity
   * @throws ConflictException          the conflict exception
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @PutMapping(path = RestAPI.UPDATE_BOOK)
  public ResponseEntity<ResponseMessage> updateBook(
      HttpServletRequest request,
      @PathVariable("bookId") String bookId,
      @RequestBody CreateUpdateBookDto createUpdateBookDto
  ) throws ConflictException, EventDoesNotExistException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      LibraryBookRecordsEntity libraryBookRecordsEntity =
          bookRecordMapper.toEntity(createUpdateBookDto);
      libraryBookService.updateBook(libraryBookRecordsEntity, bookId, id);
      return new ResponseEntity<>(
          new ResponseMessage("Book updated successfully."),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }


  /**
   * Delete book response entity.
   *
   * @param request the request
   * @param bookId  the book id
   * @return the response entity
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  @DeleteMapping(path = RestAPI.DELETE_BOOK)
  public ResponseEntity<ResponseMessage> deleteBook(
      HttpServletRequest request,
      @PathVariable("bookId") String bookId
  ) throws EventDoesNotExistException, ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      libraryBookService.deleteBook(bookId);
      return new ResponseEntity<>(
          new ResponseMessage("Book with book id:  [" + bookId + "] deleted successfully. "),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

}
