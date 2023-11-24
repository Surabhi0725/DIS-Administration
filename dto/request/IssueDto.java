package sgsits.cse.dis.administration.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>IssueDto</h1>class.
 * This class is pojo form for converting json and mapping into this java object
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2020
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {

  /**
   * The Username.
   */
  @NotBlank(message = "username cannot be empty.")
  private String username;

  /**
   * The Book id.
   */
  private String bookId;

  /**
   * The Thesis id.
   */
  private String thesisId;
}
