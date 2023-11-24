package sgsits.cse.dis.administration.controller;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sgsits.cse.dis.administration.constants.RestAPI;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.dto.request.IssueDto;
import sgsits.cse.dis.administration.dto.response.IssueResponseDto;
import sgsits.cse.dis.administration.dto.response.IssuedInformationResponse;
import sgsits.cse.dis.administration.dto.response.ResponseMessage;
import sgsits.cse.dis.administration.entity.LibraryIssueEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;
import sgsits.cse.dis.administration.feignClient.AcademicsClient;
import sgsits.cse.dis.administration.mapper.IssueMapper;
import sgsits.cse.dis.administration.service.libraryissue.LibraryIssueService;
import sgsits.cse.dis.administration.util.JwtResolverUtils;

/**
 * The type Library controller.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/libraryIssue")
public class LibraryIssueController {

  /**
   * The Library services.
   */
  @Autowired
  private LibraryIssueService libraryIssueService;

  /**
   * The Issue mapper.
   */
  @Autowired
  private IssueMapper issueMapper;

  /**
   * The Jwt resolver utils.
   */
  @Autowired
  private JwtResolverUtils jwtResolverUtils;

  /**
   * The Academics client.
   */
  @Autowired
  private AcademicsClient academicsClient;

  /**
   * Gets course list.
   *
   * @return the course list
   */
  @GetMapping(path = RestAPI.GET_COURSE_LIST)
  public ResponseEntity<List<String>> getCourseList() {
    return new ResponseEntity<>(academicsClient.getCourseList(), HttpStatus.OK);
  }

  /**
   * Issue response entity.
   *
   * @param request  the request
   * @param issueDto the issue form
   * @return the response entity
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  @PutMapping(path = RestAPI.ISSUE)
  public ResponseEntity<ResponseMessage> issueRecord(
      HttpServletRequest request,
      @RequestBody IssueDto issueDto
  ) throws EventDoesNotExistException, ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      LibraryIssueEntity issue = issueMapper.toEntity(issueDto);
      String response = libraryIssueService.issue(issue, id);
      return new ResponseEntity<>(new ResponseMessage(response), HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets no of issues.
   *
   * @param request  the request
   * @param username the username
   * @return the no of issues
   */
  @GetMapping(path = RestAPI.GET_ISSUES_BY_USERNAME)
  public ResponseEntity<List<IssueResponseDto>> getCurrentIssuesByUsername(
      HttpServletRequest request,
      @PathVariable("username") String username
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      return new ResponseEntity<>(
          libraryIssueService.getCurrentIssuesByUsername(username)
              .stream()
              .map(issueMapper::toDto)
              .collect(Collectors.toList()),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets my current issues.
   *
   * @param request the request
   * @return the my current issues
   */
  @GetMapping(path = RestAPI.GET_MY_ISSUES)
  public ResponseEntity<List<IssueResponseDto>> getMyCurrentIssues(
      HttpServletRequest request
  ) {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String username = jwtResolverUtils.getUserNameFromAuthHeader(authHeader);
    return new ResponseEntity<>(
        libraryIssueService.getCurrentIssuesByUsername(username)
            .stream()
            .map(issueMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Gets issued book info.
   *
   * @param bookId the book id
   * @return the issued book info
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ParseException             the parse exception
   */
  @GetMapping(path = RestAPI.GET_ISSUED_BOOK_INFO)
  public ResponseEntity<IssuedInformationResponse> getIssuedBookInfo(
      @PathVariable("bookId") String bookId) throws EventDoesNotExistException, ParseException {
    return new ResponseEntity<>(
        libraryIssueService.getIssuedBookInfo(bookId), HttpStatus.OK);
  }

  /**
   * Gets issued thesis info.
   *
   * @param thesisId the thesis id
   * @return the issued thesis info
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ParseException             the parse exception
   */
  @GetMapping(path = RestAPI.GET_ISSUED_THESIS_INFO)
  public ResponseEntity<IssuedInformationResponse> getIssuedThesisInfo(
      @PathVariable("thesisId") String thesisId) throws EventDoesNotExistException, ParseException {
    return new ResponseEntity<>(
        libraryIssueService.getIssuedThesisInfo(thesisId), HttpStatus.OK);
  }

  /**
   * Return book response entity.
   *
   * @param request the request
   * @param bookId  the book id
   * @return the response entity
   * @throws ParseException             the parse exception
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  @PutMapping(path = RestAPI.RETURN_BOOK)
  public ResponseEntity<ResponseMessage> returnBook(
      HttpServletRequest request,
      @PathVariable("bookId") String bookId
  ) throws ParseException, EventDoesNotExistException, ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      String response = libraryIssueService.returnBook(bookId, id);
      return new ResponseEntity<>(new ResponseMessage(response), HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Return thesis response entity.
   *
   * @param request  the request
   * @param thesisId the thesis id
   * @return the response entity
   * @throws ParseException             the parse exception
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  @PutMapping(path = RestAPI.RETURN_THESIS)
  public ResponseEntity<ResponseMessage> returnThesis(
      HttpServletRequest request,
      @PathVariable("thesisId") String thesisId
  ) throws ParseException, EventDoesNotExistException, ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      String response = libraryIssueService.returnThesis(thesisId, id);
      return new ResponseEntity<>(new ResponseMessage(response), HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets previous issues by username.
   *
   * @param request  the request
   * @param username the username
   * @return the previous issues by username
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_PREVIOUS_ISSUES_BY_USERNAME)
  public ResponseEntity<List<IssueResponseDto>> getPreviousIssuesByUsername(
      HttpServletRequest request,
      @PathVariable("username") String username
  ) throws EventDoesNotExistException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      return new ResponseEntity<>(
          libraryIssueService.getPreviousIssuesByUsername(username)
              .stream()
              .map(issueMapper::toDto)
              .collect(Collectors.toList()),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets previous issues by book id.
   *
   * @param request the request
   * @param bookId  the book id
   * @return the previous issues by book id
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_PREVIOUS_ISSUES_BY_BOOKID)
  public ResponseEntity<List<IssueResponseDto>> getPreviousIssuesByBookId(
      HttpServletRequest request,
      @PathVariable("bookId") String bookId
  ) throws EventDoesNotExistException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      return new ResponseEntity<>(
          libraryIssueService.getPreviousIssuesByBookId(bookId)
              .stream()
              .map(issueMapper::toDto)
              .collect(Collectors.toList()),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets previous issues by thesis id.
   *
   * @param request  the request
   * @param thesisId the thesis id
   * @return the previous issues by thesis id
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_PREVIOUS_ISSUES_BY_THESISID)
  public ResponseEntity<List<IssueResponseDto>> getPreviousIssuesByThesisId(
      HttpServletRequest request,
      @PathVariable("thesisId") String thesisId
  ) throws EventDoesNotExistException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      return new ResponseEntity<>(
          libraryIssueService.getPreviousIssuesByThesisId(thesisId)
              .stream()
              .map(issueMapper::toDto)
              .collect(Collectors.toList()),
          HttpStatus.OK);
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

}





