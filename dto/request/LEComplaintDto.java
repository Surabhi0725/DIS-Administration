package sgsits.cse.dis.administration.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.ComplaintType;

/**
 * The type Le complaint form.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LEComplaintDto extends ComplaintDto {

  /**
   * The Complaint type.
   */
  @JsonIgnore
  private ComplaintType complaintType = ComplaintType.LE;

  /**
   * The Lab.
   */
  @NotBlank
  private String lab;

  /**
   * The System no.
   */
  @NotBlank
  private String systemNo;

  /**
   * The Location.
   */
  @NotBlank
  private String location;
}
