package sgsits.cse.dis.administration.models;

import javax.persistence.Column;

/**
 * The type Issue model.
 */
public class IssueModel {

  /**
   * The Username.
   */
  @Column(nullable = false)
  private String username;

  /**
   * The Issue date.
   */
  @Column(nullable = false)
  private String issueDate;

  /**
   * The Expected return date.
   */
  @Column(nullable = false)
  private String expectedReturnDate;

  /**
   * The Actual return date.
   */
  @Column(nullable = false)
  private String actualReturnDate;

  /**
   * The Title.
   */
  private String title;
  /**
   * The Book id.
   */
  private String bookId;

  /**
   * The Thesis id.
   */
  private String thesisId;
  /**
   * The Penalty.
   */
  private Long penalty;
}
