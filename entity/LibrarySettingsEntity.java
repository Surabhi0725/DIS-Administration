package sgsits.cse.dis.administration.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sgsits.cse.dis.administration.models.BaseEntityModel;

/**
 * <h1><b>LibrarySettingsEntity</b> class.</h1>
 * <p>This class is model for table <b>library_settings</b> to act as DAO.
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
@Table(name = "library_settings")
public class LibrarySettingsEntity extends BaseEntityModel {

  /**
   * The Return deadline days.
   */
  @Column(nullable = false)
  private int returnDeadlineDays;

  /**
   * The No of books allowed.
   */
  @Column(nullable = false)
  private int noOfBooksAllowed;

  /**
   * The Penalty per day.
   */
  @Column(nullable = false)
  private double penaltyPerDay;

  /**
   * The With effect from.
   */
  @Column(nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date withEffectFrom;

}
