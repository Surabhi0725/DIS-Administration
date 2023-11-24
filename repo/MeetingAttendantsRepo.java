package sgsits.cse.dis.administration.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sgsits.cse.dis.administration.models.MeetingAttendants;

import java.util.List;

public interface MeetingAttendantsRepo extends JpaRepository<MeetingAttendants, String> {
    List<MeetingAttendants> findByMeetingId(String meetingId);
    void deleteByMeetingId(String meetingId);
    MeetingAttendants findByMeetingIdAndAttendantId(String meetingId , String attendantId);

    List<MeetingAttendants> findByAttendantId(String attendantId);

}
