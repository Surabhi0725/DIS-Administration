package sgsits.cse.dis.administration.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.ComplaintType;

/**
 * The type Student complaint form.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentComplaintDto extends ComplaintDto {

  /**
   * The Complaint type.
   */
  @JsonIgnore
  private ComplaintType complaintType = ComplaintType.STUDENT;

  /**
   * The Against user id.
   */
  @NotBlank
  private String againstUserId;
}
