package sgsits.cse.dis.administration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.RecordStatus;

/**
 * <h1>LibraryBookRecordsResponseDto</h1>class.
 * This class is pojo form for return java object as a response to request over network.
 *
 * @author Arjit Mishra
 * @since 27 -JAN-2020
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryBookRecordsResponseDto {

  /**
   * The Id.
   */
  private String id;

  /**
   * The Record status.
   */
  private RecordStatus recordStatus;

  /**
   * The Title.
   */
  private String title;

  /**
   * The Description.
   */
  private String description;

  /**
   * The Remarks.
   */
  private String remarks;

  /**
   * The Entry date.
   */
  private String entryDate;

  /**
   * The Isbn.
   */
  private String isbn;

  /**
   * The Subject category.
   */
  private SubjectCategoryResponseDto subjectCategory;

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
