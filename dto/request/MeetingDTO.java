package sgsits.cse.dis.administration.dto.request;


import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class MeetingDTO {
    private String meetingDate;
    private String meetingTime;
    private String hostId;
    private String meetingObjective;
    private String meetingVenue;
    private  String meetingSummary;
    private  String meetingDuration;
    private String meetingEndTime;
    private List<String> meetingAttendants;
}
