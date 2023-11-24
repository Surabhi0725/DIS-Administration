package sgsits.cse.dis.administration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyViewDTO {

    private List<sgsits.cse.dis.administration.dto.response.MeetingAndAttendanceDTO> pastMeetings;
    private List<sgsits.cse.dis.administration.dto.response.MeetingAndAttendanceDTO> currentMeetings;
    private List<sgsits.cse.dis.administration.dto.response.MeetingAndAttendanceDTO> futureMeetings;

}
