package sgsits.cse.dis.administration.constants;

/**
 * The type Rest api.
 */
public class RestAPI {

  //LIBRARY API's

  /**
   * The constant GET_COURSE_LIST.
   */
//General
  public static final String GET_COURSE_LIST = "/getCourseList";
  /**
   * The constant ISSUE.
   */
  public static final String ISSUE = "/issue";

  /**
   * The constant GET_ISSUES_BY_USERNAME.
   */
  public static final String GET_ISSUES_BY_USERNAME = "/getIssues/{username}";
  /**
   * The constant GET_MY_ISSUES.
   */
  public static final String GET_MY_ISSUES = "/getIssues";


  /**
   * The constant GET_LIBRARY_SETTINGS.
   */
//Settings
  public static final String GET_LIBRARY_SETTINGS = "/getLibrarySettings";
  /**
   * The constant UPDATE_LIBRARY_SETTINGS.
   */
  public static final String ADD_LIBRARY_SETTINGS = "/addLibrarySettings";

  /**
   * The constant ADD_BOOK.
   */
//Books
  public static final String ADD_BOOK = "/addBook";
  /**
   * The constant GET_ALL_BOOKS.
   */
  public static final String GET_ALL_BOOKS = "/getAllBooks";
  /**
   * The constant GET_BOOK_BY_TITLE.
   */
  public static final String GET_BOOK_BY_TITLE = "/getBookByTitle/{title}";
  /**
   * The constant GET_BOOK_BY_BOOK_ID.
   */
  public static final String GET_BOOK_BY_BOOK_ID = "/getBookByBookId/{bookId}";
  /**
   * The constant GET_BOOK_BY_AUTHOR_NAME.
   */
  public static final String GET_BOOK_BY_AUTHOR_NAME = "/getBookByAuthorName/{authorName}";
  /**
   * The constant UPDATE_BOOK.
   */
  public static final String UPDATE_BOOK = "/updateBook/{bookId}";
  /**
   * The constant DELETE_BOOK.
   */
  public static final String DELETE_BOOK = "/deleteBook/{bookId}";
  /**
   * The constant RETURN_BOOK.
   */
  public static final String RETURN_BOOK = "/returnBook/{bookId}";
  /**
   * The constant GET_ISSUED_BOOK_INFO.
   */
  public static final String GET_ISSUED_BOOK_INFO = "/getIssuedBookInfo/{bookId}";

  // SUBJECT CATEGORY
  /**
   * The constant ADD_BOOK_CATEGORY.
   */
  public static final String ADD_SUBJECT_CATEGORY = "/addSubjectCategory";
  /**
   * The constant DELETE_CATEGORY.
   */
  public static final String DELETE_SUBJECT_CATEGORY = "/deleteSubjectCategory/{subjectCategoryId}";
  /**
   * The constant GET_SUBJECT_CATEGORY.
   */
  public static final String GET_SUBJECT_CATEGORY = "/getSubjectCategory/{subjectCategoryId}";
  /**
   * The constant GET_SUBJECT_CATEGORY_LIST.
   */
  public static final String GET_SUBJECT_CATEGORY_LIST = "/getSubjectCategoryAcronymList";

  /**
   * The constant UPDATE_SUBJECT_CATEGORY.
   */
  public static final String UPDATE_SUBJECT_CATEGORY = "/updateSubjectCategory/{subjectCategoryId}";

  /**
   * The constant ADD_THESIS.
   */
//Thesis
  public static final String ADD_THESIS = "/addThesis";
  /**
   * The constant GET_ALL_THESIS.
   */
  public static final String GET_ALL_THESIS = "/getAllThesis";
  /**
   * The constant GET_THESIS_BY_TITLE.
   */
  public static final String GET_THESIS_BY_TITLE = "/getThesisByTitle/{title}";
  /**
   * The constant GET_THESIS_BY_SUBMITTED_BY.
   */
  public static final String GET_THESIS_BY_SUBMITTED_BY = "/getThesisBySubmittedBy/{submittedBy}";
  /**
   * The constant GET_THESIS_BY_GUIDED_BY.
   */
  public static final String GET_THESIS_BY_GUIDED_BY = "/getThesisByGuidedBy/{guidedBy}";
  /**
   * The constant GET_THESIS_BY_THESIS_ID.
   */
  public static final String GET_THESIS_BY_THESIS_ID = "/getThesisByThesisId/{thesisId}";
  /**
   * The constant GET_THESIS_BY_COURSE.
   */
  public static final String GET_THESIS_BY_COURSE = "/getThesisByCourse/{course}";
  /**
   * The constant UPDATE_THESIS.
   */
  public static final String UPDATE_THESIS = "/updateThesis/{thesisId}";
  /**
   * The constant DELETE_THESIS.
   */
  public static final String DELETE_THESIS = "/deleteThesis/{thesisId}";
  /**
   * The constant RETURN_THESIS.
   */
  public static final String RETURN_THESIS = "/returnThesis/{thesisId}";
  /**
   * The constant GET_ISSUED_THESIS_INFO.
   */
  public static final String GET_ISSUED_THESIS_INFO = "/getIssuedThesisInfo/{thesisId}";

  /**
   * The constant GET_PREVIOUS_ISSUES_BY_USERNAME.
   */
//Previous Issues
  public static final String GET_PREVIOUS_ISSUES_BY_USERNAME =
      "/getPreviousIssuesByUsername/{username}";
  /**
   * The constant GET_PREVIOUS_ISSUES_BY_BOOKID.
   */
  public static final String GET_PREVIOUS_ISSUES_BY_BOOKID = "/getPreviousIssuesByBookId/{bookId}";
  /**
   * The constant GET_PREVIOUS_ISSUES_BY_THESISID.
   */
  public static final String GET_PREVIOUS_ISSUES_BY_THESISID =
      "/getPreviousIssuesByThesisId/{thesisId}";


  public static final String UPDATE_COMPLAINT_STATUS ="/updateComplaintStatus/{complaintId}/{complaintStatus}";

  /**
   * The constant GET_TOTAL_CLEANLINESS_COMPLAINTS.
   */
  public static final String GET_COMPLAINTS =
      "/getComplaints/{complaintType}/{complaintStatus}";
  public static final String GET_ALL_COMPLAINTS =
          "/getAllComplaints";

  public static final String GET_COMPLAINT_COUNT =
          "/getComplaintCount";
  public static final String GET_COMPLAINT_PENDING_STATES_ =
          "/getComplaintPendingStates";


  /**
   * The constant GET_COMPLAINTS_BY_USER_ASSIGNED_TO.
   */
  public static final String GET_COMPLAINTS_BY_USER_ASSIGNED_TO = "/getMyAssignedComplaints";

  public static final String GET_STUDENT_FULL_NAME_LIST = "/getStudentFullNameList";

  /**
   * The constant GET_COMPLAINTS_CREATED_BY_USER.
   */
  public static final String GET_COMPLAINTS_CREATED_BY_USER = "/getMyCreatedComplaints";
  /**
   * The constant GET_COMPLAINTS_CREATED_BY_USER.
   */
  public static final String GET_COMPLAINTS_CREATED_BY_USER_TYPE = "/getMyCreatedComplaints/{complaintType}";
  /**
   * The constant GET_STUDENT_LIST.
   */
  public static final String GET_STUDENT_LIST = "/getStudentList";
  /**
   * The constant GET_COMPLAINTS_BY_USER_ASSIGNED_TO_COUNT.
   */
  public static final String GET_COMPLAINTS_BY_USER_ASSIGNED_TO_COUNT = "/getComplaintsCount";

  /**
   * The constant ADD_ASSIGNEES_TO_COMPLAINT.
   */
  public static final String ADD_ASSIGNEES_TO_COMPLAINT = "/addAssignees/{complaintId}";
  /**
   * The constant RESOLVE_COMPLAINT.
   */
  public static final String RESOLVE_COMPLAINT = "/resolveComplaint/{complaintId}";
  /**
   * The constant REJECT_COMPLAINT.
   */
  public static final String REJECT_COMPLAINT = "/rejectComplaint/{complaintId}";

  /**
   * The constant ADD_CLEANLINESS_COMPLAINTS.
   */
  public static final String ADD_CLEANLINESS_COMPLAINTS = "/addCleanlinessComplaint";
  /**
   * The constant ADD_LE_COMPLAINTS.
   */
  public static final String ADD_LE_COMPLAINTS = "/addLEComplaint";
  /**
   * The constant ADD_OTHER_COMPLAINTS.
   */
  public static final String ADD_OTHER_COMPLAINTS = "/addOtherComplaint";
  /**
   * The constant ADD_FACULTY_COMPLAINTS.
   */
  public static final String ADD_FACULTY_COMPLAINTS = "/addFacultyComplaint";
  /**
   * The constant ADD_STUDENT_COMPLAINTS.
   */
  public static final String ADD_STUDENT_COMPLAINTS = "/addStudentComplaint";
  /**
   * The constant ADD_CWN_COMPLAINTS.
   */
  public static final String ADD_CWN_COMPLAINTS = "/addCWNComplaint";
  /**
   * The constant ADD_ECCW_COMPLAINTS.
   */
  public static final String ADD_ECCW_COMPLAINTS = "/addECCWComplaint";
  /**
   * The constant ADD_EMRS_COMPLAINTS.
   */
  public static final String ADD_EMRS_COMPLAINTS = "/addEMRSComplaint";
  /**
   * The constant ADD_TELEPHONE_COMPLAINTS.
   */
  public static final String ADD_TELEPHONE_COMPLAINTS = "/addTelephoneComplaint";

  /**
   * The constant ADD_FACULTY_RESOURCE_REQUEST.
   */
//Faculty Resources
  public static final String ADD_FACULTY_RESOURCE_REQUEST = "/addFacultyResourceRequest";
  /**
   * The constant GET_FACULTY_RESOURCE_REQUEST.
   */
  public static final String GET_FACULTY_RESOURCE_REQUEST = "/getFacultyResourceRequest/{id}";
  /**
   * The constant EDIT_FACULTY_RESOURCE_REQUEST.
   */
  public static final String EDIT_FACULTY_RESOURCE_REQUEST = "/editFacultyResourceRequest/{id}";
  /**
   * The constant GET_ALL_FACULTY_REQUESTS_FOR_ID.
   */
  public static final String GET_ALL_FACULTY_REQUESTS_FOR_ID = "/getAllFacultyRequestsForId";
  /**
   * The constant GET_ALL_FACULTY_REQUESTS_RESOLVED.
   */
  public static final String GET_ALL_FACULTY_REQUESTS_RESOLVED = "/getAllResolvedFacultyRequests";
  /**
   * The constant GET_ALL_FACULTY_REQUESTS_UNRESOLVED.
   */
  public static final String GET_ALL_FACULTY_REQUESTS_UNRESOLVED =
      "/getAllUnresolvedFacultyRequests";
  /**
   * The constant SET_FACULTY_REQUEST_RESOLVED.
   */
  public static final String SET_FACULTY_REQUEST_RESOLVED = "/setFacultyRequestResolved/{id}";
}
