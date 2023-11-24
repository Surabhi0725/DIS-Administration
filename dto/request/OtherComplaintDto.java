package sgsits.cse.dis.administration.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.ComplaintType;

/**
 * The type Other complaint form.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtherComplaintDto extends ComplaintDto {

  /**
   * The Complaint type.
   */
  @JsonIgnore
  private ComplaintType complaintType = ComplaintType.OTHER;

  /**
   * The Location.
   */
  private String location;
}
