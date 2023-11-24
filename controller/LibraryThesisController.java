package sgsits.cse.dis.administration.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sgsits.cse.dis.administration.constants.RestAPI;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.dto.request.CreateUpdateThesisDto;
import sgsits.cse.dis.administration.dto.response.LibraryThesisRecordsResponseDto;
import sgsits.cse.dis.administration.dto.response.ResponseMessage;
import sgsits.cse.dis.administration.entity.LibraryThesisRecordsEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;
import sgsits.cse.dis.administration.mapper.ThesisRecordMapper;
import sgsits.cse.dis.administration.service.librarythesis.LibraryThesisService;
import sgsits.cse.dis.administration.util.JwtResolverUtils;

/**
 * The type Library thesis controller.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/libraryThesis")
public class LibraryThesisController {

  /**
   * The Library thesis service.
   */
  @Autowired
  private LibraryThesisService libraryThesisService;

  /**
   * The Jwt resolver utils.
   */
  @Autowired
  private JwtResolverUtils jwtResolverUtils;

  /**
   * The Thesis record mapper.
   */
  @Autowired
  private ThesisRecordMapper thesisRecordMapper;


  /**
   * Add thesis response entity.
   *
   * @param request       the request
   * @param addThesisForm the add thesis form
   * @return the response entity
   * @throws ConflictException the conflict exception
   */
  @PostMapping(path = RestAPI.ADD_THESIS)
  public ResponseEntity<ResponseMessage> addThesis(HttpServletRequest request,
      @RequestBody CreateUpdateThesisDto addThesisForm)
      throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      LibraryThesisRecordsEntity libraryThesisRecordsEntity =
          thesisRecordMapper.toEntity(addThesisForm);
      libraryThesisService.addThesis(libraryThesisRecordsEntity, id);
      return new ResponseEntity<>(
          new ResponseMessage("Thesis added successfully."),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets all thesiss.
   *
   * @return the all thesiss
   */
  @GetMapping(path = RestAPI.GET_ALL_THESIS)
  public ResponseEntity<List<LibraryThesisRecordsResponseDto>> getAllThesis() {
    return new ResponseEntity<>(
        libraryThesisService.getAllThesis()
            .stream()
            .map(thesisRecordMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Gets thesis by title.
   *
   * @param title the title
   * @return the thesis by title
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_THESIS_BY_TITLE)
  public ResponseEntity<List<LibraryThesisRecordsResponseDto>> getThesisByTitle(
      @PathVariable("title") String title) throws EventDoesNotExistException {
    return new ResponseEntity<>(
        libraryThesisService.getThesisByTitle(title)
            .stream()
            .map(thesisRecordMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Gets thesis by thesis id.
   *
   * @param thesisId the thesis id
   * @return the thesis by thesis id
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_THESIS_BY_THESIS_ID, produces = "application/json")
  public ResponseEntity<LibraryThesisRecordsResponseDto> getThesisByThesisId(
      @PathVariable("thesisId") String thesisId) throws EventDoesNotExistException {
    LibraryThesisRecordsEntity libraryThesisRecordsEntity =
        libraryThesisService.getThesisByThesisId(thesisId);
    LibraryThesisRecordsResponseDto libraryThesisRecordsResponseDto =
        thesisRecordMapper.toDto(libraryThesisRecordsEntity);
    return new ResponseEntity<>(libraryThesisRecordsResponseDto, HttpStatus.OK);
  }


  /**
   * Gets thesis by author name.
   *
   * @param guidedBy the guided by
   * @return the thesis by author name
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_THESIS_BY_GUIDED_BY)
  public ResponseEntity<List<LibraryThesisRecordsResponseDto>> getThesisByGuidedBy(
      @PathVariable("guidedBy") String guidedBy) throws EventDoesNotExistException {
    return new ResponseEntity<>(
        libraryThesisService.getThesisByGuidedBy(guidedBy)
            .stream()
            .map(thesisRecordMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Gets thesis by course.
   *
   * @param course the course
   * @return the thesis by course
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_THESIS_BY_COURSE)
  public ResponseEntity<List<LibraryThesisRecordsResponseDto>> getThesisByCourse(
      @PathVariable("course") String course) throws EventDoesNotExistException {
    return new ResponseEntity<>(
        libraryThesisService.getThesisByCourse(course)
            .stream()
            .map(thesisRecordMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Gets thesis by submitted by.
   *
   * @param submittedBy the submitted by
   * @return the thesis by submitted by
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_THESIS_BY_SUBMITTED_BY)
  public ResponseEntity<List<LibraryThesisRecordsResponseDto>> getThesisBySubmittedBy(
      @PathVariable("submittedBy") String submittedBy) throws EventDoesNotExistException {
    return new ResponseEntity<>(
        libraryThesisService.getThesisBySubmittedBy(submittedBy)
            .stream()
            .map(thesisRecordMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Update thesis response entity.
   *
   * @param request               the request
   * @param thesisId              the thesis id
   * @param createUpdateThesisDto the create update thesis dto
   * @return the response entity
   * @throws ConflictException          the conflict exception
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @PutMapping(path = RestAPI.UPDATE_THESIS)
  public ResponseEntity<ResponseMessage> updateThesis(
      HttpServletRequest request,
      @PathVariable("thesisId") String thesisId,
      @RequestBody CreateUpdateThesisDto createUpdateThesisDto
  ) throws ConflictException, EventDoesNotExistException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      LibraryThesisRecordsEntity libraryThesisRecordsEntity =
          thesisRecordMapper.toEntity(createUpdateThesisDto);
      libraryThesisService.updateThesis(libraryThesisRecordsEntity, thesisId, id);
      return new ResponseEntity<>(
          new ResponseMessage("Thesis updated successfully."),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }


  /**
   * Delete thesis response entity.
   *
   * @param request  the request
   * @param thesisId the thesis id
   * @return the response entity
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  @DeleteMapping(path = RestAPI.DELETE_THESIS, produces = "text/plain")
  public ResponseEntity<ResponseMessage> deleteThesis(
      HttpServletRequest request,
      @PathVariable("thesisId") String thesisId
  )
      throws EventDoesNotExistException, ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      libraryThesisService.deleteThesis(thesisId);
      return new ResponseEntity<>(
          new ResponseMessage("Thesis with thesis id:  [" + thesisId + "] deleted successfully. "),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

}
