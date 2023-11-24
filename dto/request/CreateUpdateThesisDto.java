package sgsits.cse.dis.administration.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.dto.response.SubjectCategoryResponseDto;

/**
 * <h1>CreateUpdateThesisDto</h1>class.
 * This class is pojo form for converting json and mapping into this java object
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2020
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateThesisDto {

  /**
   * The Year.
   */
  private String year;

  /**
   * The Submitted by.
   */
  @NotBlank(message = "Submitted By cannot be empty.")
  private String submittedBy;

  /**
   * The Guided by.
   */
  @NotBlank(message = "Guided By cannot be empty.")
  private String guidedBy;

  /**
   * The Cd status.
   */
  private Boolean cdStatus;

  /**
   * The Course.
   */
  @NotBlank(message = "Course cannot be empty.")
  private String course;

  /**
   * The Title.
   */
  @NotBlank(message = "Title cannot be empty.")
  private String title;

  /**
   * The Remarks.
   */
  private String remarks;

  /**
   * The Subject category.
   */
  @NotBlank(message = "Subject Category cannot be empty.")
  private SubjectCategoryResponseDto subjectCategory;
}
