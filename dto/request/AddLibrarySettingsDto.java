package sgsits.cse.dis.administration.dto.request;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <h1>AddLibrarySettingsDto</h1>class.
 * This class is pojo form for converting json and mapping into this java object
 *
 * @author Ajinkya Taranekar
 * @since 5 -MAR-2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddLibrarySettingsDto {

  /**
   * The Return deadline days.
   */
  @NotBlank
  private long returnDeadlineDays;

  /**
   * The No of books allowed.
   */
  @NotBlank
  private long noOfBooksAllowed;

  /**
   * The Penalty per day.
   */
  @NotBlank
  private long penaltyPerDay;

  /**
   * The With effect from.
   */
  @NotBlank
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date withEffectFrom;
}
