package sgsits.cse.dis.administration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.FacultyRequestStatus;

/**
 * The type Faculty request response dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacultyRequestResponseDto {

  /**
   * The Id.
   */
  private String id;

  /**
   * The Category.
   */
  private String category;

  /**
   * The Assigned person id.
   */
  private String assignedPersonId;

  /**
   * The Assigned person username.
   */
  private String assignedPersonUsername;

  /**
   * The Status.
   */
  private FacultyRequestStatus status;
  /**
   * The Username.
   */
  private String username;
  /**
   * The Details.
   */
  private String details;
}
