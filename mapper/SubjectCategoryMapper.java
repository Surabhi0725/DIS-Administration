package sgsits.cse.dis.administration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sgsits.cse.dis.administration.dto.request.CreateUpdateSubjectCategoryDto;
import sgsits.cse.dis.administration.dto.response.SubjectCategoryResponseDto;
import sgsits.cse.dis.administration.entity.LibrarySubjectCategoryEntity;

/**
 * The interface Complaint mapper.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectCategoryMapper {

  /**
   * To entity library subject category entity.
   *
   * @param subjectCategoryDto the subject category dto
   * @return the library subject category entity
   */
  LibrarySubjectCategoryEntity toEntity(
      CreateUpdateSubjectCategoryDto subjectCategoryDto);

  /**
   * To dto subject category response dto.
   *
   * @param librarySubjectCategoryEntity the library subject category entity
   * @return the subject category response dto
   */
  SubjectCategoryResponseDto toDto(
      LibrarySubjectCategoryEntity librarySubjectCategoryEntity);
}
