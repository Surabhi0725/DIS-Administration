package sgsits.cse.dis.administration.dto.response;

import lombok.*;
import sgsits.cse.dis.administration.models.MeetingAttendants;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingAttendantsDTO {
    private String name;
    private MeetingAttendants meetingAttendants;
}
