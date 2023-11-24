package sgsits.cse.dis.administration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.models.RecordEntityModel;

/**
 * <h1><b>LibraryBookRecordsEntity</b> class.</h1>
 * <p>This class is model for table <b>library_book_records</b> to act as DAO.
 * This table contains book records.
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
@Table(name = "library_book_records")
public class LibraryBookRecordsEntity extends RecordEntityModel {

  /**
   * The Title.
   */
  @Column(nullable = false)
  private String title;

  /**
   * The Isbn.
   */
  private String isbn;
  /**
   * The Author name.
   */
  private String authorName;
  /**
   * The Year of publication.
   */
  private String yearOfPublication;
  /**
   * The Edition.
   */
  private String edition;
  /**
   * The Publisher and place.
   */
  private String publisherAndPlace;

  /**
   * The No of pages.
   */
  private Long noOfPages;
  /**
   * The Price.
   */
  private Double price;

  /**
   * The Purchase date.
   */
  private String purchaseDate;

}
