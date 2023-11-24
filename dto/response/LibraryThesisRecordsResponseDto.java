package sgsits.cse.dis.administration.dto.response;

import javax.persistence.Column;
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
public class LibraryThesisRecordsResponseDto {

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
   * The Course.
   */
  @Column(nullable = false)
  public String course;
  /**
   * The Id.
   */
  private String id;
  /**
   * The Record status.
   */
  private RecordStatus recordStatus = RecordStatus.AVAILABLE;
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
   * The Subject category.
   */
  private SubjectCategoryResponseDto subjectCategory;

}
