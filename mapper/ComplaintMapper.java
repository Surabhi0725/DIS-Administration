package sgsits.cse.dis.administration.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sgsits.cse.dis.administration.dto.request.CWNComplaintDto;
import sgsits.cse.dis.administration.dto.request.CleanlinessComplaintDto;
import sgsits.cse.dis.administration.dto.request.ECCWComplaintDto;
import sgsits.cse.dis.administration.dto.request.EMRComplaintDto;
import sgsits.cse.dis.administration.dto.request.FacultyComplaintDto;
import sgsits.cse.dis.administration.dto.request.LEComplaintDto;
import sgsits.cse.dis.administration.dto.request.OtherComplaintDto;
import sgsits.cse.dis.administration.dto.request.StudentComplaintDto;
import sgsits.cse.dis.administration.dto.request.TelephoneComplaintDto;
import sgsits.cse.dis.administration.dto.response.ComplaintResponseDto;
import sgsits.cse.dis.administration.entity.ComplaintEntity;

/**
 * The interface Complaint mapper.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComplaintMapper {

  /**
   * To entity complaint entity.
   *
   * @param cleanlinessComplaintDto the cleanliness complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      CleanlinessComplaintDto cleanlinessComplaintDto);

  /**
   * To entity complaint entity.
   *
   * @param cwnComplaintDto the cwn complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      CWNComplaintDto cwnComplaintDto);

  /**
   * To entity complaint entity.
   *
   * @param eccwComplaintDto the eccw complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      ECCWComplaintDto eccwComplaintDto);

  /**
   * To entity complaint entity.
   *
   * @param emrComplaintDto the emr complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      EMRComplaintDto emrComplaintDto);

  /**
   * To entity complaint entity.
   *
   * @param facultyComplaintDto the faculty complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      FacultyComplaintDto facultyComplaintDto);

  /**
   * To entity complaint entity.
   *
   * @param leComplaintDto the le complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      LEComplaintDto leComplaintDto);

  /**
   * To entity complaint entity.
   *
   * @param otherComplaintDto the other complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      OtherComplaintDto otherComplaintDto);

  /**
   * To entity complaint entity.
   *
   * @param studentComplaintDto the student complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      StudentComplaintDto studentComplaintDto);

  /**
   * To entity complaint entity.
   *
   * @param telephoneComplaintDto the telephone complaint dto
   * @return the complaint entity
   */
  ComplaintEntity toEntity(
      TelephoneComplaintDto telephoneComplaintDto);

  /**
   * To dto complaint response dto.
   *
   * @param complaintEntity the complaint entity
   * @return the complaint response dto
   */
  ComplaintResponseDto toDto(
      ComplaintEntity complaintEntity);
}