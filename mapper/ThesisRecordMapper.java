package sgsits.cse.dis.administration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sgsits.cse.dis.administration.dto.request.CreateUpdateThesisDto;
import sgsits.cse.dis.administration.dto.response.LibraryThesisRecordsResponseDto;
import sgsits.cse.dis.administration.entity.LibraryThesisRecordsEntity;

/**
 * The interface Complaint mapper.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ThesisRecordMapper {

  /**
   * To entity library thesis records entity.
   *
   * @param createUpdateThesisDto the create update thesis dto
   * @return the library thesis records entity
   */
  LibraryThesisRecordsEntity toEntity(
      CreateUpdateThesisDto createUpdateThesisDto);

  /**
   * To dto library thesis records response dto.
   *
   * @param libraryThesisRecordsEntity the library thesis records entity
   * @return the library thesis records response dto
   */
  LibraryThesisRecordsResponseDto toDto(
      LibraryThesisRecordsEntity libraryThesisRecordsEntity);
}
