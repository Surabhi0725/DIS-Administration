package sgsits.cse.dis.administration.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Complaint dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDto {

  /**
   * The Title.
   */
  @NotBlank
  private String title;
  /**
   * The Details.
   */
  @NotBlank
  private String details;
  /**
   * The Remarks.
   */
  @NotBlank
  private String remarks;

}
