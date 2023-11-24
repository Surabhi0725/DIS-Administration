package sgsits.cse.dis.administration.service.librarysettings;

import sgsits.cse.dis.administration.entity.LibrarySettingsEntity;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;

/**
 * The interface Library settings service.
 */
public interface LibrarySettingsService {

  /**
   * Gets current setting.
   *
   * @return the current setting
   */
  LibrarySettingsEntity getCurrentSetting();

  /**
   * Add settings.
   *
   * @param librarySettings the library settings
   * @param createdBy       the created by
   * @throws EventDoesNotExistException the event does not exist exception
   */
  void addSettings(LibrarySettingsEntity librarySettings, String createdBy)
      throws EventDoesNotExistException;

}
