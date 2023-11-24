package sgsits.cse.dis.administration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.models.Meetings;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingAndAttendanceDTO {

    private Meetings meetings;

    private  int isPresent;

}
