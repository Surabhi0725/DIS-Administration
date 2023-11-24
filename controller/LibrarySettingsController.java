package sgsits.cse.dis.administration.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sgsits.cse.dis.administration.constants.RestAPI;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.dto.request.CreateLibrarySettingsDto;
import sgsits.cse.dis.administration.dto.response.LibrarySettingsResponseDto;
import sgsits.cse.dis.administration.dto.response.ResponseMessage;
import sgsits.cse.dis.administration.entity.LibrarySettingsEntity;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.exception.ResourceRequestNotAccessibleException;
import sgsits.cse.dis.administration.mapper.SettingsMapper;
import sgsits.cse.dis.administration.service.librarysettings.LibrarySettingsService;
import sgsits.cse.dis.administration.util.JwtResolverUtils;

/**
 * The type Library settings controller.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/librarySettings")
public class LibrarySettingsController {

  /**
   * The Library settings service.
   */
  @Autowired
  private LibrarySettingsService librarySettingsService;

  /**
   * The Jwt resolver utils.
   */
  @Autowired
  private JwtResolverUtils jwtResolverUtils;

  /**
   * The Settings mapper.
   */
  @Autowired
  private SettingsMapper settingsMapper;

  /**
   * Add settings response entity.
   *
   * @param request            the request
   * @param librarySettingsDto the library settings dto
   * @return the response entity
   * @throws ConflictException          the conflict exception
   * @throws EventDoesNotExistException the event does not exist exception
   */
  @PostMapping(path = RestAPI.ADD_LIBRARY_SETTINGS)
  public ResponseEntity<ResponseMessage> addSettings(HttpServletRequest request,
      @RequestBody CreateLibrarySettingsDto librarySettingsDto)
      throws ConflictException, EventDoesNotExistException {
    String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
    String id = jwtResolverUtils.getIdFromAuthHeader(authHeader);
    String userType = jwtResolverUtils.getUserTypeFromAuthHeader(authHeader);
    if (!userType.equalsIgnoreCase(SecurityConstants.STUDENT)) {
      LibrarySettingsEntity librarySettings = settingsMapper.toEntity(librarySettingsDto);
      librarySettingsService.addSettings(librarySettings, id);
      return new ResponseEntity<>(
          new ResponseMessage("Settings added successfully."),
          HttpStatus.OK
      );
    }
    throw new ResourceRequestNotAccessibleException(SecurityConstants.UNAUTHORIZED_MESSAGE);
  }

  /**
   * Gets current settings.
   *
   * @return the current settings
   */
  @GetMapping(path = RestAPI.GET_LIBRARY_SETTINGS)
  public ResponseEntity<LibrarySettingsResponseDto> getCurrentSettings() {
    LibrarySettingsEntity librarySettings = librarySettingsService.getCurrentSetting();
    LibrarySettingsResponseDto librarySettingsResponseDto = settingsMapper.toDto(librarySettings);
    return new ResponseEntity<>(
        librarySettingsResponseDto,
        HttpStatus.OK);
  }
}
