package sgsits.cse.dis.administration.models;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.entity.LibrarySubjectCategoryEntity;
import sgsits.cse.dis.administration.enums.RecordStatus;

/**
 * The type Record entity model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntityModel extends BaseEntityModel {

  /**
   * The Record status.
   */
  private RecordStatus recordStatus = RecordStatus.AVAILABLE;

  /**
   * The Description.
   */
  @Column(nullable = false)
  private String description;

  /**
   * The Remarks.
   */
  private String remarks;

  /**
   * The Entry date.
   */
  @Column(nullable = false)
  private String entryDate;

  /**
   * The Subject category.
   */
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "subject_category_id")
  private LibrarySubjectCategoryEntity subjectCategory;

}
