package sgsits.cse.dis.administration.service.librarysettings;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgsits.cse.dis.administration.entity.LibrarySettingsEntity;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.repo.LibrarySettingsRepository;
import sgsits.cse.dis.administration.service.librarysettings.LibrarySettingsService;
import sgsits.cse.dis.administration.util.CalenderUtils;

/**
 * The type Library settings service.
 */
@Service
public class LibrarySettingsServiceImpl implements LibrarySettingsService {

  /**
   * The Library settings repository.
   */
  @Autowired
  private LibrarySettingsRepository librarySettingsRepository;

  /**
   * Start of date date.
   *
   * @param date the date
   * @return the date
   */
  private Date startOfDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 5);
    cal.set(Calendar.MINUTE, 30);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  @Override
  public LibrarySettingsEntity getCurrentSetting() {
    LibrarySettingsEntity librarySettings = null;
    Date date = new Date();
    long minimumWithEffectFromDifference = Long.MAX_VALUE;
    long minimumCreatedDateDifference = Long.MAX_VALUE;
    for (LibrarySettingsEntity librarySettingsEntity : librarySettingsRepository.findAll()) {
      if (!CalenderUtils.datePassedAway(librarySettingsEntity.getWithEffectFrom())) {
        long withEffectFromDifference =
            Math.abs(
                startOfDate(date).getTime() - librarySettingsEntity.getWithEffectFrom().getTime());
        long createdDateDifference =
            Math.abs(date.getTime() - librarySettingsEntity.getCreatedDate().getTime());
        if (withEffectFromDifference <= minimumWithEffectFromDifference) {
          minimumWithEffectFromDifference = withEffectFromDifference;
          if (createdDateDifference < minimumCreatedDateDifference) {
            minimumCreatedDateDifference = createdDateDifference;
            librarySettings = librarySettingsEntity;
          }
        }
      }
    }
    return librarySettings;
  }

  @Override
  public void addSettings(LibrarySettingsEntity librarySettings, String createdBy)
      throws EventDoesNotExistException {
    if (!CalenderUtils.datePassedAway(librarySettings.getWithEffectFrom())) {
      librarySettings.setCreatedBy(createdBy);
      librarySettings.setLastModifiedBy(createdBy);
      librarySettingsRepository.save(librarySettings);
    } else {
      throw new EventDoesNotExistException("Event has already passed");
    }
  }
}
