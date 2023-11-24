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
public class IssueResponseDto {

  /**
   * The Id.
   */
  private String id;

  /**
   * The Username.
   */
  private String username;

  /**
   * The Issue date.
   */
  private Date issueDate;

  /**
   * The Expected return date.
   */
  private Date expectedReturnDate;

  /**
   * The Actual return date.
   */
  private Date actualReturnDate;

  /**
   * The Is returned.
   */
  private Boolean isReturned;

  /**
   * The Book id.
   */
  private String bookId;

  /**
   * The Thesis id.
   */
  private String thesisId;

  /**
   * The Penalty amount to be applied.
   */
  private Double penaltyAmountToBeApplied;

  /**
   * The Penalty amount to be paid.
   */
  private Double penaltyAmountToBePaid;
}
