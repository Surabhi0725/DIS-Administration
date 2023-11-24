package sgsits.cse.dis.administration.service.librarybook;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sgsits.cse.dis.administration.entity.LibraryBookRecordsEntity;
import sgsits.cse.dis.administration.enums.RecordStatus;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.repo.LibraryBookRecordsRepository;
import sgsits.cse.dis.administration.service.librarybook.LibraryBookService;
import sgsits.cse.dis.administration.util.GeneralUtils;

/**
 * The type Library book service.
 */
@Service
public class LibraryBookServiceImpl
    implements LibraryBookService {

  /**
   * The Library book records repository.
   */
  @Autowired
  private LibraryBookRecordsRepository libraryBookRecordsRepository;

  /**
   * The General utils.
   */
  @Autowired
  private GeneralUtils generalUtils;

  @Override
  public String addBook(LibraryBookRecordsEntity libraryBookRecordsEntity, String createdBy)
      throws ConflictException {
    libraryBookRecordsEntity.setCreatedBy(createdBy);
    libraryBookRecordsEntity.setLastModifiedBy(createdBy);
    libraryBookRecordsRepository.save(libraryBookRecordsEntity);
    return "Book added successfully.";
  }

  @Override
  public List<LibraryBookRecordsEntity> getAllBooks() {
    return (List<LibraryBookRecordsEntity>) libraryBookRecordsRepository.findAll();
  }

  @Override
  public List<LibraryBookRecordsEntity> getBookByTitle(String title)
      throws EventDoesNotExistException {
    return libraryBookRecordsRepository.findByTitleContainingIgnoreCase(title);
  }

  @Override
  public LibraryBookRecordsEntity getBookByBookId(String bookId)
      throws EventDoesNotExistException {
    return libraryBookRecordsRepository.findById(bookId)
        .orElseThrow(() -> new EventDoesNotExistException(
            "Book with book id: " + bookId + " doesn't exist."));
  }

  @Override
  public List<LibraryBookRecordsEntity> getBookByAuthorName(String authorName)
      throws EventDoesNotExistException {
    return libraryBookRecordsRepository.findByAuthorNameContainingIgnoreCase(authorName);
  }

  @Override
  public void updateBook(LibraryBookRecordsEntity libraryBookRecordsEntity, String bookId,
      String updatedBy)
      throws EventDoesNotExistException, ConflictException {
    if (libraryBookRecordsRepository.existsById(bookId)) {
      LibraryBookRecordsEntity libraryBookRecordsEntityFromDb = getBookByBookId(bookId);
      libraryBookRecordsEntityFromDb.setLastModifiedBy(updatedBy);
      generalUtils.copyNotNullProperties(libraryBookRecordsEntity, libraryBookRecordsEntityFromDb);
      libraryBookRecordsRepository.save(libraryBookRecordsEntityFromDb);
    } else {
      throw new EventDoesNotExistException("Book with book id: " + bookId + " doesn't exist.");
    }
  }

  @Override
  @Transactional
  public void deleteBook(String bookId) throws EventDoesNotExistException, ConflictException {
    LibraryBookRecordsEntity libraryBookRecordsEntity = getBookByBookId(bookId);
    if (!libraryBookRecordsEntity.getRecordStatus().equals(RecordStatus.ISSUED)) {
      libraryBookRecordsRepository.deleteById(bookId);
    } else {
      throw new EventDoesNotExistException("Book with book id: " + bookId + " doesn't exist.");
    }
  }
}
