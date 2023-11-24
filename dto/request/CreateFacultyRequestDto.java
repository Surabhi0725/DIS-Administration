package sgsits.cse.dis.administration.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Faculty request form.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFacultyRequestDto {

  /**
   * The Category.
   */
  @NotBlank
  private String category;

  /**
   * The Details.
   */
  @NotBlank
  private String details;
}
