package sgsits.cse.dis.administration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sgsits.cse.dis.administration.dto.request.IssueDto;
import sgsits.cse.dis.administration.dto.response.IssueResponseDto;
import sgsits.cse.dis.administration.entity.LibraryIssueEntity;

/**
 * The interface Complaint mapper.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IssueMapper {

  /**
   * To entity library issue entity.
   *
   * @param issueDto the issue dto
   * @return the library issue entity
   */
  LibraryIssueEntity toEntity(
      IssueDto issueDto);

  /**
   * To dto issue response dto.
   *
   * @param libraryIssueEntity the library issue entity
   * @return the issue response dto
   */
  IssueResponseDto toDto(
      LibraryIssueEntity libraryIssueEntity);
}
