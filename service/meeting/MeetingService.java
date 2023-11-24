package sgsits.cse.dis.administration.service.meeting;

import sgsits.cse.dis.administration.dto.request.MeetingAttendeeDTO;
import sgsits.cse.dis.administration.dto.request.MeetingDTO;
import sgsits.cse.dis.administration.dto.response.FacultyViewDTO;
import sgsits.cse.dis.administration.dto.response.HODMeetingResponseDTO;
import sgsits.cse.dis.administration.dto.response.MeetingAttendantsDTO;
import sgsits.cse.dis.administration.models.MeetingAttendants;

import java.text.ParseException;
import java.util.List;

public interface MeetingService {

    String createMeeting(MeetingDTO meetingDTO);
    String updateMeeting(MeetingDTO meetingDTO , String meetingId, String type);
    List<HODMeetingResponseDTO> getAllMeetings();

    List<HODMeetingResponseDTO> getFutureMeetings();
    List<HODMeetingResponseDTO> getPastMeetings();
    List<HODMeetingResponseDTO> getCurrentMeetings();

    FacultyViewDTO getAllMeetingsByFacultyId(String facultyId);



    public String deleteMeetingById(String id);
    String markMeetingAttendance(String meetingId , MeetingAttendeeDTO[] attendees);


    boolean isFutureMeeting(String time);
    boolean isPastMeeting(String time) throws ParseException;
    boolean isCurrentMeeting(String time, String endTime);

    List<MeetingAttendantsDTO> getAllMeetingAttendants(String meetingId);
    List<MeetingAttendantsDTO> getPastMeetingAttendants(String meetingId);
//     List<String> getAllMeetingAttendantsNames(String meetingId);
//     List<String> getPastMeetingAttendantsNames(String meetingId);
}
