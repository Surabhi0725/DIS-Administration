package sgsits.cse.dis.administration.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.models.BaseEntityModel;

/**
 * <h1><b>LibraryIssue</b> class.</h1>
 * <p>This class is model for table <b>library_issue_</b> to act as DAO.
 * This table conatins issue  of library books and htesis.
 *
 * @author Arjit Mishra.
 * @version 1.0.
 * @since 2 -DEC-2019.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "library_issue")
public class LibraryIssueEntity extends BaseEntityModel {

  /**
   * The Username.
   */
  @Column(nullable = false)
  private String username;

  /**
   * The Issue date.
   */
  @Column(nullable = false)
  private Date issueDate;

  /**
   * The Expected return date.
   */
  @Column(nullable = false)
  private Date expectedReturnDate;

  /**
   * The Actual return date.
   */
  private Date actualReturnDate;

  /**
   * The Is returned.
   */
  private Boolean isReturned = false;

  /**
   * The Book id.
   */
  private String bookId;

  /**
   * The Thesis id.
   */
  private String thesisId;

  /**
   * The Penalty.
   */
  private Double penaltyAmountToBeApplied;

  /**
   * The Penalty amount to be paid.
   */
  private Double penaltyAmountToBePaid;
}
