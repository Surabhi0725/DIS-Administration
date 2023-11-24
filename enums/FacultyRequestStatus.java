package sgsits.cse.dis.administration.enums;

/**
 * The enum Complaint status.
 */
public enum FacultyRequestStatus {

  /**
   * Pending complaint status.
   */
  PENDING("Pending"),
  /**
   * The In progress.
   */
  IN_PROGRESS("In Progress"),
  /**
   * Resolved complaint status.
   */
  RESOLVED("Resolved"),
  /**
   * Rejected complaint status.
   */
  REJECTED("Rejected");

  /**
   * The Complaint status.
   */
  private final String complaintStatus;

  /**
   * Instantiates a new Complaint status.
   *
   * @param complaintStatus the complaint status
   */
  FacultyRequestStatus(String complaintStatus) {
    this.complaintStatus = complaintStatus;
  }

  /**
   * String to enum complaint status.
   *
   * @param name the name
   * @return the complaint status
   */
  public static FacultyRequestStatus stringToEnum(String name) {
    for (FacultyRequestStatus status : FacultyRequestStatus.values()) {
      if (status.getComplaintStatus().equalsIgnoreCase(name)) {
        return status;
      }
    }
    throw new IllegalArgumentException("No complaint status with name " + name + " found");
  }

  /**
   * Gets complaint status.
   *
   * @return the complaint status
   */
  public String getComplaintStatus() {
    return complaintStatus;
  }
}
