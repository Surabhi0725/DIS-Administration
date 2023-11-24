package sgsits.cse.dis.administration.dto.response;

import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.ComplaintStatus;
import sgsits.cse.dis.administration.enums.ComplaintType;

/**
 * The type Complaint general response.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponseDto {

  /**
   * The Id.
   */
  private String id;

  /**
   * The Title.
   */
  private String title;
  /**
   * The Details.
   */
  private String details;
  /**
   * The Remarks.
   */
  private String remarks;

  /**
   * The Complaint status.
   */
  private ComplaintStatus complaintStatus;
  /**
   * The Complaint type.
   */
  private ComplaintType complaintType;

  /**
   * The Date of resolution.
   */
  private Date dateOfResolution;

  /**
   * The Location for complaint.
   */
  private String location;

  /**
   * The Lab.
   */
  private String lab;
  /**
   * The System no.
   */
  private String systemNo;

  /**
   * The Form id.
   */
  private String formId;
  /**
   * The Pdf url.
   */
  private String pdfUrl;
  /**
   * The Telecom extension no.
   */
  private String telecomExtensionNo;

  /**
   * The Assigned user ids.
   */
  private Set<String> assignedUserIds;


  /**
   * The Closed by.
   */
  private String closedBy;
  /**
   * The Closed date.
   */
  private Date closedDate;
  /**
   * The Closed remarks.
   */
  private String closedRemarks;

  /**
   * The Against user id.
   */
  private String againstUserId;

  /**
   * The Against user Full name.
   */
  private String againstUserFullName;

  /**
   * The Against userName.
   */
  private String againstUserName;
}

