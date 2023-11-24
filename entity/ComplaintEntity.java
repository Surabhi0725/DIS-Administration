package sgsits.cse.dis.administration.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sgsits.cse.dis.administration.enums.ComplaintStatus;
import sgsits.cse.dis.administration.enums.ComplaintType;
import sgsits.cse.dis.administration.models.BaseEntityModel;


/**
 * The type Complaint entity.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "complaintsV2")
public class ComplaintEntity extends BaseEntityModel {

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
  @Enumerated(value = EnumType.STRING)
  private ComplaintStatus complaintStatus;
  /**
   * The Complaint type.
   */
  @Enumerated(value = EnumType.STRING)
  private ComplaintType complaintType;

  /**
   * The Date of resolution.
   */
  private Date dateOfResolution;

  /**
   * The Location.
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
  @ElementCollection
  private Set<String> assignedUserIds;


  /**
   * The Closed by.
   */
  private String closedBy;

  /**
   * The Closed date.
   */
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date closedDate;
  /**
   * The Closed remarks.
   */
  private String closedRemarks;

  /**
   * The Against user id.
   */
  private String againstUserId;
}
