package sgsits.cse.dis.administration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Complaint report response dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintReportResponseDto {

  /**
   * The All.
   */
  private StatusAndType all;
  /**
   * The My.
   */
  private StatusAndType my;
}

