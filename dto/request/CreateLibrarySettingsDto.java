package sgsits.cse.dis.administration.dto.request;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
public class CreateLibrarySettingsDto {

  /**
   * The Return deadline days.
   */
  @NotBlank
  private int returnDeadlineDays;

  /**
   * The No of books allowed.
   */
  @NotBlank
  private int noOfBooksAllowed;

  /**
   * The Penalty per day.
   */
  @NotBlank
  private double penaltyPerDay;

  /**
   * The With effect from.
   */
  @NotBlank
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date withEffectFrom;
}
