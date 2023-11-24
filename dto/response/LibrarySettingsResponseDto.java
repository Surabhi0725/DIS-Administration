package sgsits.cse.dis.administration.dto.response;

import java.util.Date;
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
public class LibrarySettingsResponseDto {

  /**
   * The Return deadline days.
   */
  private int returnDeadlineDays;

  /**
   * The No of books allowed.
   */
  private int noOfBooksAllowed;

  /**
   * The Penalty per day.
   */
  private double penaltyPerDay;

  /**
   * The With effect from.
   */
  private Date withEffectFrom;
}
