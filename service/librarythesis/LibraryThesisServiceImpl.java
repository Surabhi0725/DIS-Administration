package sgsits.cse.dis.administration.service.librarythesis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sgsits.cse.dis.administration.entity.LibraryThesisRecordsEntity;
import sgsits.cse.dis.administration.enums.RecordStatus;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.repo.LibraryThesisRecordsRepository;
import sgsits.cse.dis.administration.service.librarythesis.LibraryThesisService;
import sgsits.cse.dis.administration.util.GeneralUtils;

/**
 * The type Library thesis service.
 */
@Service
public class LibraryThesisServiceImpl
    implements LibraryThesisService {

  /**
   * The Library thesis records repository.
   */
  @Autowired
  private LibraryThesisRecordsRepository libraryThesisRecordsRepository;

  /**
   * The General utils.
   */
  @Autowired
  private GeneralUtils generalUtils;

  @Override
  public String addThesis(LibraryThesisRecordsEntity libraryThesisRecordsEntity, String createdBy)
      throws ConflictException {
    libraryThesisRecordsEntity.setCreatedBy(createdBy);
    libraryThesisRecordsEntity.setLastModifiedBy(createdBy);
    libraryThesisRecordsRepository.save(libraryThesisRecordsEntity);
    return "Thesis added successfully.";
  }

  @Override
  public List<LibraryThesisRecordsEntity> getAllThesis() {
    return (List<LibraryThesisRecordsEntity>) libraryThesisRecordsRepository.findAll();
  }

  @Override
  public List<LibraryThesisRecordsEntity> getThesisByTitle(String title)
      throws EventDoesNotExistException {
    return libraryThesisRecordsRepository.findByTitleContainingIgnoreCase(title);
  }

  @Override
  public List<LibraryThesisRecordsEntity> getThesisBySubmittedBy(String submittedBy)
      throws EventDoesNotExistException {
    return libraryThesisRecordsRepository.findBySubmittedByContainingIgnoreCase(submittedBy);
  }

  @Override
  public List<LibraryThesisRecordsEntity> getThesisByGuidedBy(String guidedBy)
      throws EventDoesNotExistException {
    return libraryThesisRecordsRepository.findByGuidedByContainingIgnoreCase(guidedBy);
  }

  @Override
  public LibraryThesisRecordsEntity getThesisByThesisId(String thesisId)
      throws EventDoesNotExistException {
    return libraryThesisRecordsRepository.findById(thesisId)
        .orElseThrow(() -> new EventDoesNotExistException(
            "Thesis with thesis id: " + thesisId + " doesn't exist."));
  }

  @Override
  public List<LibraryThesisRecordsEntity> getThesisByCourse(String course)
      throws EventDoesNotExistException {
    return libraryThesisRecordsRepository.findByCourseContainingIgnoreCase(course);
  }


  @Override
  public void updateThesis(LibraryThesisRecordsEntity libraryThesisRecordsEntity, String thesisId,
      String updatedBy)
      throws EventDoesNotExistException, ConflictException {
    if (libraryThesisRecordsRepository.existsById(thesisId)) {
      LibraryThesisRecordsEntity libraryThesisRecordsEntityFromDb = getThesisByThesisId(thesisId);
      libraryThesisRecordsEntityFromDb.setLastModifiedBy(updatedBy);
      generalUtils.copyNotNullProperties(libraryThesisRecordsEntity,
          libraryThesisRecordsEntityFromDb);
      libraryThesisRecordsRepository.save(libraryThesisRecordsEntityFromDb);
    } else {
      throw new EventDoesNotExistException(
          "Thesis with thesis id: " + thesisId + " doesn't exist.");
    }
  }

  @Override
  @Transactional
  public void deleteThesis(String thesisId) throws EventDoesNotExistException, ConflictException {
    LibraryThesisRecordsEntity libraryThesisRecordsEntity = getThesisByThesisId(thesisId);
    if (!libraryThesisRecordsEntity.getRecordStatus().equals(RecordStatus.ISSUED)) {
      libraryThesisRecordsRepository.deleteById(thesisId);
    } else {
      throw new EventDoesNotExistException(
          "Thesis with thesis id: " + thesisId + " doesn't exist.");
    }
  }
}
