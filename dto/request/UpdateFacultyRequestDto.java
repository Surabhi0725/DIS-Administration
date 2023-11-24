package sgsits.cse.dis.administration.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Faculty request edit form.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFacultyRequestDto {

  /**
   * The Assigned person id.
   */
  @NotBlank
  private String assignedPersonId;
}
