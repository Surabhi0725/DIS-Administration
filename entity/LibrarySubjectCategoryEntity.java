package sgsits.cse.dis.administration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "library_subject_category")
public class LibrarySubjectCategoryEntity extends BaseEntityModel {

  /**
   * The Name.
   */
  @Column(nullable = false)
  private String name;

  /**
   * The Description.
   */
  @Column(nullable = false)
  private String description;

  /**
   * The Icon url.
   */
  private String iconUrl;

}
