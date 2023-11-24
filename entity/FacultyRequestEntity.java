package sgsits.cse.dis.administration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.FacultyRequestStatus;
import sgsits.cse.dis.administration.models.BaseEntityModel;

/**
 * The type Faculty request entity.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resource_request_category")
public class FacultyRequestEntity extends BaseEntityModel {

  /**
   * The Category.
   */
  @Column(nullable = false)
  private String category;

  /**
   * The Assigned person id.
   */
  @Column(nullable = false)
  private String assignedPersonId;

  /**
   * The Assigned person username.
   */
  @Column(nullable = false)
  private String assignedPersonUsername;

  /**
   * The Status.
   */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private FacultyRequestStatus status;

  /**
   * The Username.
   */
  @Column(nullable = false)
  private String username;

  /**
   * The Details.
   */
  @Column(nullable = false)
  private String details;
}
