package sgsits.cse.dis.administration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sgsits.cse.dis.administration.dto.request.CreateFacultyRequestDto;
import sgsits.cse.dis.administration.dto.request.UpdateFacultyRequestDto;
import sgsits.cse.dis.administration.dto.response.FacultyRequestResponseDto;
import sgsits.cse.dis.administration.entity.FacultyRequestEntity;

/**
 * The interface Faculty request mapper.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FacultyRequestMapper {

  /**
   * To entity faculty request entity.
   *
   * @param createFacultyRequestDto the create faculty request dto
   * @return the faculty request entity
   */
  FacultyRequestEntity toEntity(
      CreateFacultyRequestDto createFacultyRequestDto);

  /**
   * To entity faculty request entity.
   *
   * @param updateFacultyRequestDto the update faculty request dto
   * @return the faculty request entity
   */
  FacultyRequestEntity toEntity(
      UpdateFacultyRequestDto updateFacultyRequestDto);

  /**
   * To dto faculty request response dto.
   *
   * @param facultyRequestEntity the faculty request entity
   * @return the faculty request response dto
   */
  FacultyRequestResponseDto toDto(
      FacultyRequestEntity facultyRequestEntity);
}
