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
import sgsits.cse.dis.administration.dto.request.CreateUpdateSubjectCategoryDto;
import sgsits.cse.dis.administration.dto.response.ResponseMessage;
import sgsits.cse.dis.administration.dto.response.SubjectCategoryResponseDto;
import sgsits.cse.dis.administration.entity.LibrarySubjectCategoryEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;
import sgsits.cse.dis.administration.mapper.SubjectCategoryMapper;
import sgsits.cse.dis.administration.service.librarysubjectcategory.LibrarySubjectCategoryService;
import sgsits.cse.dis.administration.util.JwtResolverUtils;

/**
 * The type Library subjectCategory controller.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/librarySubjectCategory")
public class LibrarySubjectCategoryController {

  /**
   * The Library subjectCategory service.
   */
  @Autowired
  private LibrarySubjectCategoryService librarySubjectCategoryService;

  /**
   * The Jwt resolver utils.
   */
  @Autowired
  private JwtResolverUtils jwtResolverUtils;

  /**
   * The SubjectCategory record mapper.
   */
  @Autowired
  private SubjectCategoryMapper subjectCategoryMapper;

  /**
   * Add subjectCategory response entity.
   *
   * @param request            the request
   * @param subjectCategoryDto the subject category dto
   * @return the response entity
   * @throws ConflictException the conflict exception
   */
  @PostMapping(path = RestAPI.ADD_SUBJECT_CATEGORY)
  public ResponseEntity<ResponseMessage> addSubjectCategory(
      HttpServletRequest request,
      @RequestBody CreateUpdateSubjectCategoryDto subjectCategoryDto
  ) throws ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      LibrarySubjectCategoryEntity librarySubjectCategoryEntity =
          subjectCategoryMapper.toEntity(subjectCategoryDto);
      librarySubjectCategoryService.addNewSubjectCategory(librarySubjectCategoryEntity, id);
      return new ResponseEntity<>(
          new ResponseMessage("Subject Category added successfully."),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets all subjectCategory.
   *
   * @return the all subjectCategory
   */
  @GetMapping(path = RestAPI.GET_SUBJECT_CATEGORY_LIST)
  public ResponseEntity<List<SubjectCategoryResponseDto>> getAllSubjectCategory() {
    return new ResponseEntity<>(
        librarySubjectCategoryService.getSubjectCategoryAcronymList()
            .stream()
            .map(subjectCategoryMapper::toDto)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  /**
   * Gets subject category.
   *
   * @param subjectCategoryId the subject category id
   * @return the subject category
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @GetMapping(path = RestAPI.GET_SUBJECT_CATEGORY)
  public ResponseEntity<SubjectCategoryResponseDto> getSubjectCategory(
      @PathVariable("subjectCategoryId") String subjectCategoryId
  ) throws EventDoesNotExistException {
    LibrarySubjectCategoryEntity librarySubjectCategoryEntity =
        librarySubjectCategoryService.getSubjectCategory(subjectCategoryId);
    SubjectCategoryResponseDto subjectCategoryResponseDto =
        subjectCategoryMapper.toDto(librarySubjectCategoryEntity);
    return new ResponseEntity<>(
        subjectCategoryResponseDto,
        HttpStatus.OK);
  }

  /**
   * Update subjectCategory response entity.
   *
   * @param request            the request
   * @param subjectCategoryId  the subjectCategory id
   * @param subjectCategoryDto the subject category dto
   * @return the response entity
   * @throws ConflictException          the conflict exception
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @PutMapping(path = RestAPI.UPDATE_SUBJECT_CATEGORY)
  public ResponseEntity<ResponseMessage> updateSubjectCategory(
      HttpServletRequest request,
      @PathVariable("subjectCategoryId") String subjectCategoryId,
      @RequestBody CreateUpdateSubjectCategoryDto subjectCategoryDto
  ) throws ConflictException, EventDoesNotExistException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      LibrarySubjectCategoryEntity librarySubjectCategoryEntity =
          subjectCategoryMapper.toEntity(subjectCategoryDto);
      librarySubjectCategoryService.updateSubjectCategory(librarySubjectCategoryEntity,
          subjectCategoryId, id);
      return new ResponseEntity<>(
          new ResponseMessage("Subject Category updated successfully."),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }


  /**
   * Delete subjectCategory response entity.
   *
   * @param request           the request
   * @param subjectCategoryId the subjectCategory id
   * @return the response entity
   * @throws EventDoesNotExistException the event does not exist exception
   * @throws ConflictException          the conflict exception
   */
  @DeleteMapping(path = RestAPI.DELETE_SUBJECT_CATEGORY, produces = "text/plain")
  public ResponseEntity<ResponseMessage> deleteSubjectCategory(
      HttpServletRequest request,
      @PathVariable("subjectCategoryId") String subjectCategoryId
  )
      throws EventDoesNotExistException, ConflictException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      librarySubjectCategoryService.deleteSubjectCategory(subjectCategoryId);
      return new ResponseEntity<>(
          new ResponseMessage("SubjectCategory with subjectCategory id:  [" + subjectCategoryId
              + "] deleted successfully. "),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

}
