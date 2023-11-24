package sgsits.cse.dis.administration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sgsits.cse.dis.administration.dto.request.CreateLibrarySettingsDto;
import sgsits.cse.dis.administration.dto.response.LibrarySettingsResponseDto;
import sgsits.cse.dis.administration.entity.LibrarySettingsEntity;

/**
 * The interface Complaint mapper.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SettingsMapper {

  /**
   * To entity library settings entity.
   *
   * @param librarySettingsDto the library settings dto
   * @return the library settings entity
   */
  LibrarySettingsEntity toEntity(
      CreateLibrarySettingsDto librarySettingsDto);

  /**
   * To dto library settings response dto.
   *
   * @param librarySettings the library settings
   * @return the library settings response dto
   */
  LibrarySettingsResponseDto toDto(
      LibrarySettingsEntity librarySettings);
}
