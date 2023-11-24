package sgsits.cse.dis.administration.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.ComplaintType;

/**
 * The type Faculty complaint form.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacultyComplaintDto extends ComplaintDto {

  /**
   * The Complaint type.
   */
  @JsonIgnore
  private ComplaintType complaintType = ComplaintType.FACULTY;

  /**
   * The Against user id.
   */
  @NotBlank
  private String againstUserId;

}
