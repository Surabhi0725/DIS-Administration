package sgsits.cse.dis.administration.repo;

import org.springframework.data.repository.CrudRepository;
import sgsits.cse.dis.administration.entity.LibrarySubjectCategoryEntity;

/**
 * <h1>LibrarySettingsRepository</h1> interface.
 * this repository contains Jpafunciton to perform crud operation.
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2019
 */
public interface LibrarySubjectCategoryRepository
    extends CrudRepository<LibrarySubjectCategoryEntity, String> {

}
