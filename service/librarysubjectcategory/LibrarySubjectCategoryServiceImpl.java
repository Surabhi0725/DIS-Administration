package sgsits.cse.dis.administration.service.librarysubjectcategory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgsits.cse.dis.administration.entity.LibrarySubjectCategoryEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.repo.LibrarySubjectCategoryRepository;
import sgsits.cse.dis.administration.service.librarysubjectcategory.LibrarySubjectCategoryService;
import sgsits.cse.dis.administration.util.GeneralUtils;

/**
 * The type Library subject category service.
 */
@Service
public class LibrarySubjectCategoryServiceImpl implements LibrarySubjectCategoryService {

  /**
   * The Library subject category repository.
   */
  @Autowired
  private LibrarySubjectCategoryRepository librarySubjectCategoryRepository;

  /**
   * The General utils.
   */
  @Autowired
  private GeneralUtils generalUtils;

  @Override
  public List<LibrarySubjectCategoryEntity> getSubjectCategoryAcronymList() {
    return (List<LibrarySubjectCategoryEntity>) librarySubjectCategoryRepository.findAll();
  }

  @Override
  public void addNewSubjectCategory(LibrarySubjectCategoryEntity librarySubjectCategoryEntity,
      String createdBy)
      throws ConflictException {
    librarySubjectCategoryEntity.setCreatedBy(createdBy);
    librarySubjectCategoryEntity.setLastModifiedBy(createdBy);
    librarySubjectCategoryRepository.save(librarySubjectCategoryEntity);
  }

  @Override
  public void deleteSubjectCategory(String subjectCategoryId) throws EventDoesNotExistException {
    LibrarySubjectCategoryEntity librarySubjectCategoryEntity =
        getSubjectCategory(subjectCategoryId);
    librarySubjectCategoryRepository.deleteById(subjectCategoryId);
  }

  @Override
  public void updateSubjectCategory(LibrarySubjectCategoryEntity librarySubjectCategoryEntity,
      String subjectCategoryId, String updatedBy)
      throws EventDoesNotExistException {
    LibrarySubjectCategoryEntity librarySubjectCategoryEntityFromDb =
        getSubjectCategory(subjectCategoryId);
    generalUtils.copyNotNullProperties(librarySubjectCategoryEntity,
        librarySubjectCategoryEntityFromDb);
    librarySubjectCategoryEntityFromDb.setLastModifiedBy(updatedBy);
    librarySubjectCategoryRepository.save(librarySubjectCategoryEntityFromDb);
  }

  @Override
  public LibrarySubjectCategoryEntity getSubjectCategory(String subjectCategoryId)
      throws EventDoesNotExistException {
    return librarySubjectCategoryRepository.findById(subjectCategoryId).orElseThrow(
        () -> new EventDoesNotExistException("Subject Category does not exist")
    );
  }

}
