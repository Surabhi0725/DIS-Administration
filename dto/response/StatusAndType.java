package sgsits.cse.dis.administration.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.ComplaintStatus;
import sgsits.cse.dis.administration.enums.ComplaintType;

/**
 * The type Status and type.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusAndType {

  /**
   * The Status.
   */
  private Map<ComplaintStatus, Integer> status;
  /**
   * The Type.
   */
  private Map<ComplaintType, Integer> type;
}
