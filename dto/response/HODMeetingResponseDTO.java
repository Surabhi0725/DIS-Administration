package sgsits.cse.dis.administration.dto.response;


import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HODMeetingResponseDTO {

    private String meetingDate;
    private String meetingId;
    private String meetingTime;
    private String meetingEndTime;
    private String hostId;
    private String meetingObjective;
    private String meetingVenue;
    private  String meetingSummary;
    private  String meetingDuration;
    private List<String> meetingAttendants;

}
