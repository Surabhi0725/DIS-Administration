package sgsits.cse.dis.administration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sgsits.cse.dis.administration.dto.request.CreateUpdateBookDto;
import sgsits.cse.dis.administration.dto.response.LibraryBookRecordsResponseDto;
import sgsits.cse.dis.administration.entity.LibraryBookRecordsEntity;

/**
 * The interface Complaint mapper.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookRecordMapper {

  /**
   * To entity library book records entity.
   *
   * @param createUpdateBookDto the create update book dto
   * @return the library book records entity
   */
  LibraryBookRecordsEntity toEntity(
      CreateUpdateBookDto createUpdateBookDto);

  /**
   * To dto library book records response dto.
   *
   * @param libraryBookRecordsEntity the library book records entity
   * @return the library book records response dto
   */
  LibraryBookRecordsResponseDto toDto(
      LibraryBookRecordsEntity libraryBookRecordsEntity);

}
