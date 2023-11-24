package sgsits.cse.dis.administration.dto.request;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class MeetingAttendanceDTO {

    private String meetingId;
    private MeetingAttendeeDTO[] attendees;
}
