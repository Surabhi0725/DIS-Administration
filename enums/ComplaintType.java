package sgsits.cse.dis.administration.enums;

/**
 * The enum Complaint type.
 */
public enum ComplaintType {

  /**
   * Cwn complaint type.
   */
  CWN("CWN"),
  /**
   * Cleanliness complaint type.
   */
  CLEANLINESS("Cleanliness"),
  /**
   * The Eccw.
   */
  ECCW("Engineering Cell and Central Workshop"),
  /**
   * The Emr.
   */
  EMR("Electrical Maintenance and Repairs"),
  /**
   * Faculty complaint type.
   */
  FACULTY("Faculty"),
  /**
   * The Le.
   */
  LE("Lab Equipment"),
  /**
   * Student complaint type.
   */
  STUDENT("Student"),
  /**
   * Telephone complaint type.
   */
  TELEPHONE("Telephone"),
  /**
   * Other complaint type.
   */
  OTHER("Other"),

  ALL("All");
  /**
   * The Complaint type.
   */
  private final String complaintType;

  /**
   * Instantiates a new Complaint type.
   *
   * @param complaintType the complaint type
   */
  ComplaintType(String complaintType) {
    this.complaintType = complaintType;
  }

  /**
   * String to enum complaint type.
   *
   * @param name the name
   * @return the complaint type
   */
  public static ComplaintType stringToEnum(String name) {
    for (ComplaintType status : ComplaintType.values()) {
      if (status.getComplaintType().equalsIgnoreCase(name)) {
        return status;
      }
    }
    throw new IllegalArgumentException("No complaint type with name " + name + " found");
  }

  /**
   * Gets complaint type.
   *
   * @return the complaint type
   */
  public String getComplaintType() {
    return complaintType;
  }
}
