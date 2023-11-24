package sgsits.cse.dis.administration.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.dto.response.SubjectCategoryResponseDto;

/**
 * <h1>CreateUpdateBookDto</h1>class.
 * This class is pojo form for converting json and mapping into this java object
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2020
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateBookDto {

  /**
   * The Isbn.
   */
  private String isbn;

  /**
   * The Subject category.
   */
  @NotBlank(message = "Subject Category cannot be empty.")
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
  private long noOfPages;

  /**
   * The Price.
   */
  private Double price;

  /**
   * The Purchase date.
   */
  private String purchaseDate;

  /**
   * The Title.
   */
  @NotBlank(message = "Title cannot be empty.")
  private String title;

  /**
   * The Description.
   */
  @NotBlank(message = "Description cannot be empty.")
  private String description;

  /**
   * The Remarks.
   */
  private String remarks;
}
