package sgsits.cse.dis.administration.service.libraryissue;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sgsits.cse.dis.administration.dto.response.IssuedInformationResponse;
import sgsits.cse.dis.administration.entity.LibraryBookRecordsEntity;
import sgsits.cse.dis.administration.entity.LibraryIssueEntity;
import sgsits.cse.dis.administration.entity.LibrarySettingsEntity;
import sgsits.cse.dis.administration.entity.LibraryThesisRecordsEntity;
import sgsits.cse.dis.administration.enums.RecordStatus;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.feignClient.UserClient;
import sgsits.cse.dis.administration.repo.LibraryIssueRepository;
import sgsits.cse.dis.administration.service.librarybook.LibraryBookService;
import sgsits.cse.dis.administration.service.librarysettings.LibrarySettingsService;
import sgsits.cse.dis.administration.service.librarythesis.LibraryThesisService;
import sgsits.cse.dis.administration.util.CalenderUtils;

/**
 * The type Library issue service.
 */
@Service
public class LibraryIssueServiceImpl implements LibraryIssueService {

  /**
   * The Library issue repository.
   */
  @Autowired
  private LibraryIssueRepository libraryIssueRepository;

  /**
   * The Library thesis service.
   */
  @Autowired
  private LibraryThesisService libraryThesisService;

  /**
   * The Library book service.
   */
  @Autowired
  private LibraryBookService libraryBookService;

  /**
   * The Library settings service.
   */
  @Autowired
  private LibrarySettingsService librarySettingsService;

  /**
   * The User client.
   */
  @Autowired
  private UserClient userClient;

  @Override
  public List<LibraryIssueEntity> getPreviousIssuesByUsername(String username)
      throws EventDoesNotExistException {
    return libraryIssueRepository.findByUsernameIgnoreCaseAndIsReturned(username, Boolean.TRUE);
  }

  @Override
  public List<LibraryIssueEntity> getPreviousIssuesByBookId(String bookId)
      throws EventDoesNotExistException {
    return libraryIssueRepository.findByBookIdAndIsReturned(bookId, Boolean.TRUE);
  }

  @Override
  public List<LibraryIssueEntity> getPreviousIssuesByThesisId(String thesisId)
      throws EventDoesNotExistException {
    return libraryIssueRepository.findByThesisIdAndIsReturned(thesisId, Boolean.TRUE);
  }

  @Transactional
  @Override
  public String issue(LibraryIssueEntity libraryIssueEntity, String createdBy)
      throws EventDoesNotExistException, ConflictException {
    if (userClient.existsByUsername(libraryIssueEntity.getUsername())) {
      if (libraryIssueEntity.getBookId() != null
          && libraryIssueEntity.getThesisId() != null) {
        throw new ConflictException("Can't issue both at same time");
      }
      if (libraryIssueEntity.getBookId() == null
          && libraryIssueEntity.getThesisId() == null) {
        throw new ConflictException("Need book/thesis for issue");
      }
      LibrarySettingsEntity librarySettings = librarySettingsService.getCurrentSetting();
      if (getCurrentIssuesByUsername(libraryIssueEntity.getUsername()).size()
          >= librarySettings.getNoOfBooksAllowed()) {
        throw new ConflictException(
            "This book can't be issued since " + librarySettings.getNoOfBooksAllowed()
                + " already issued.");
      }
      if (libraryIssueEntity.getThesisId() != null) {
        LibraryThesisRecordsEntity libraryThesisRecords =
            libraryThesisService.getThesisByThesisId(libraryIssueEntity.getThesisId());
        libraryThesisRecords.setRecordStatus(RecordStatus.ISSUED);
        libraryThesisService.updateThesis(libraryThesisRecords, libraryIssueEntity.getThesisId(),
            createdBy);
      } else {
        LibraryBookRecordsEntity libraryBookRecords =
            libraryBookService.getBookByBookId(libraryIssueEntity.getBookId());
        libraryBookRecords.setRecordStatus(RecordStatus.ISSUED);
        libraryBookService.updateBook(libraryBookRecords, libraryIssueEntity.getBookId(),
            createdBy);
      }
      libraryIssueEntity.setIssueDate(new Date());
      libraryIssueEntity.setExpectedReturnDate(
          CalenderUtils.addDays(new Date(), librarySettings.getReturnDeadlineDays()));
      libraryIssueEntity.setPenaltyAmountToBeApplied(librarySettings.getPenaltyPerDay());
      libraryIssueEntity.setCreatedBy(createdBy);
      libraryIssueEntity.setLastModifiedBy(createdBy);
      libraryIssueRepository.save(libraryIssueEntity);
    } else {
      throw new EventDoesNotExistException(
          "username with " + libraryIssueEntity.getUsername() + " not found.");
    }
    return "Issue successful";
  }


  @Override
  public List<LibraryIssueEntity> getCurrentIssuesByUsername(String username) {
    return libraryIssueRepository.findByUsernameIgnoreCaseAndIsReturned(username, Boolean.FALSE);
  }


  @Transactional
  @Override
  public String returnBook(String bookId, String modifiedBy)
      throws ParseException, EventDoesNotExistException, ConflictException {
    LibraryIssueEntity libraryCurrentIssues =
        libraryIssueRepository.findByBookIdAndIsReturned(bookId, Boolean.FALSE).get(0);
    try {
      libraryCurrentIssues.setLastModifiedBy(modifiedBy);
      libraryCurrentIssues.setActualReturnDate(new Date());
      libraryCurrentIssues.setPenaltyAmountToBePaid(
          getPenalty(
              libraryCurrentIssues.getActualReturnDate(),
              libraryCurrentIssues.getExpectedReturnDate(),
              libraryCurrentIssues.getPenaltyAmountToBeApplied()
          )
      );
      libraryCurrentIssues.setIsReturned(Boolean.TRUE);
      libraryIssueRepository.save(libraryCurrentIssues);
    } catch (ParseException e) {
      throw new ParseException("Unable to get penalty", 0);
    }
    //Change Book status to available.
    LibraryBookRecordsEntity libraryBookRecordsEntity = libraryBookService.getBookByBookId(bookId);
    libraryBookRecordsEntity.setRecordStatus(RecordStatus.AVAILABLE);
    libraryBookService.updateBook(libraryBookRecordsEntity, bookId, modifiedBy);
    return "Return Successful";
  }


  @Transactional
  @Override
  public String returnThesis(String thesisId, String modifiedBy)
      throws ParseException, EventDoesNotExistException, ConflictException {
    LibraryIssueEntity libraryCurrentIssues =
        libraryIssueRepository.findByThesisIdAndIsReturned(thesisId, Boolean.FALSE).get(0);
    try {
      libraryCurrentIssues.setLastModifiedBy(modifiedBy);
      libraryCurrentIssues.setActualReturnDate(new Date());
      libraryCurrentIssues.setPenaltyAmountToBePaid(
          getPenalty(
              libraryCurrentIssues.getActualReturnDate(),
              libraryCurrentIssues.getExpectedReturnDate(),
              libraryCurrentIssues.getPenaltyAmountToBeApplied()
          )
      );
      libraryCurrentIssues.setIsReturned(Boolean.TRUE);
      libraryIssueRepository.save(libraryCurrentIssues);
    } catch (ParseException e) {
      throw new ParseException("Unable to get penalty", 0);
    }
    //Change Book status to available.
    LibraryThesisRecordsEntity libraryThesisRecords =
        libraryThesisService.getThesisByThesisId(thesisId);
    libraryThesisRecords.setRecordStatus(RecordStatus.AVAILABLE);
    libraryThesisService.updateThesis(libraryThesisRecords, thesisId, modifiedBy);
    return "Return Successful";
  }


  @Override
  public IssuedInformationResponse getIssuedBookInfo(String bookId)
      throws EventDoesNotExistException, ParseException {
    LibraryIssueEntity libraryCurrentIssues =
        libraryIssueRepository.findByBookIdAndIsReturned(bookId, Boolean.FALSE).get(0);
    if (libraryCurrentIssues == null) {
      throw new EventDoesNotExistException(
          "No current issue  for thesis with id [" + bookId + "]");
    }
    return new IssuedInformationResponse(libraryCurrentIssues.getUsername(),
        getPenalty(libraryCurrentIssues.getIssueDate(), new Date(),
            libraryCurrentIssues.getPenaltyAmountToBeApplied()));
  }


  @Override
  public IssuedInformationResponse getIssuedThesisInfo(String thesisId)
      throws EventDoesNotExistException, ParseException {
    LibraryIssueEntity libraryCurrentIssues =
        libraryIssueRepository.findByThesisIdAndIsReturned(thesisId, Boolean.FALSE).get(0);
    if (libraryCurrentIssues == null) {
      throw new EventDoesNotExistException(
          "No current issue for thesis with id [" + thesisId + "]");
    }
    return new IssuedInformationResponse(libraryCurrentIssues.getUsername(),
        getPenalty(libraryCurrentIssues.getIssueDate(), new Date(),
            libraryCurrentIssues.getPenaltyAmountToBeApplied()));
  }

  /**
   * Gets penalty.
   *
   * @param actualReturnDate         the actual return date
   * @param expectedReturnDate       the expected return date
   * @param penaltyAmountToBeApplied the penalty amount to be applied
   * @return the penalty
   * @throws ParseException the parse exception
   */
  double getPenalty(Date actualReturnDate, Date expectedReturnDate, Double penaltyAmountToBeApplied)
      throws ParseException {
    long daysIssued = CalenderUtils.getDaysBetweenDates(actualReturnDate, expectedReturnDate);
    return daysIssued * penaltyAmountToBeApplied;
  }
}
