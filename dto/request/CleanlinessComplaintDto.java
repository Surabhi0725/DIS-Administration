package sgsits.cse.dis.administration.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.ComplaintType;

/**
 * <h1>CleanlinessComplaintDto</h1>class.
 * This class is pojo form for converting json and mapping into this java object
 *
 * @author Arjit Mishra
 * @since 2 -DEC-2020
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CleanlinessComplaintDto extends ComplaintDto {

  /**
   * The Complaint type.
   */
  @JsonIgnore
  private ComplaintType complaintType = ComplaintType.CLEANLINESS;

  /**
   * The Location.
   */
  @NotBlank
  private String location;
}
