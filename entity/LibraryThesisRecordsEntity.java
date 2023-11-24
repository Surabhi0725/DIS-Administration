package sgsits.cse.dis.administration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.models.RecordEntityModel;

/**
 * <h1><b>LibraryThesisRecordsEntity</b> class.</h1>
 * <p>This class is model for table <b>library_thesis_records</b> to act as DAO.
 *
 * @author Arjit Mishra.
 * @version 1.0.
 * @since 2 -DEC-2019.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "library_thesis_records")
public class LibraryThesisRecordsEntity extends RecordEntityModel {

  /**
   * The Year.
   */
  public String year;
  /**
   * The Submitted by.
   */
  public String submittedBy;
  /**
   * The Guided by.
   */
  public String guidedBy;
  /**
   * The Cd status.
   */
  public Boolean cdStatus;
  /**
   * The Title.
   */
  @Column(nullable = false)
  private String title;
  /**
   * The Course.
   */
  private String course;
}
