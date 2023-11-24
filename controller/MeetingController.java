package sgsits.cse.dis.administration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sgsits.cse.dis.administration.dto.request.MeetingAttendanceDTO;
import sgsits.cse.dis.administration.dto.request.MeetingDTO;
import sgsits.cse.dis.administration.dto.request.RequestMessage;
import sgsits.cse.dis.administration.dto.response.*;
import sgsits.cse.dis.administration.service.meeting.MeetingService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/meeting")
@CrossOrigin(origins="*")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;


    @PostMapping("/createMeeting")
    public ResponseEntity<Object> createMeeting(@RequestBody MeetingDTO meetingDTO) {
        try {
            System.out.println(meetingDTO);
           // String startTime = meetingDTO.getMeetingTime().length()==2?meetingDTO.getMeetingTime():"0"+meetingDTO.getMeetingTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
            meetingDTO.setMeetingDate(sdf.format(sdf2.parse(meetingDTO.getMeetingDate())));
            meetingDTO.setMeetingTime(meetingDTO.getMeetingTime().length()==2?meetingDTO.getMeetingTime():"0"+meetingDTO.getMeetingTime());
            String fullTime = meetingDTO.getMeetingDate() + " " + meetingDTO.getMeetingTime();
            System.out.println(fullTime);
//            SimpleDateFormat sdf3  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            Date mt = sdf3.parse(fullTime);
//            Date now = new Date();
//            System.out.println(now);
//            System.out.println(sdf3.format(now));
//            if(mt.before(now)){
//                System.out.println("past");
//            }else{
//                System.out.println("not past");
//            }
            if(meetingService.isPastMeeting(fullTime)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Cannot create past meeting"));
            }else {
                System.out.println("not past meeting");
            }
         //   meetingDTO.setMeetingTime(startTime);
            String response = meetingService.createMeeting(meetingDTO);
            return ResponseEntity.ok().body(new ResponseMessage(response));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Server Error"));
        }
    }

    @GetMapping("/getAllMeetings")
    public ResponseEntity<Object> getAllMeetings()
    {
        try {
            return ResponseEntity.ok(meetingService.getAllMeetings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Failed to fetch all meetings"));
        }
    }

    @GetMapping("/getFutureMeetings")
    public ResponseEntity<Object> getFutureMeetings()
    {
        try {
            return ResponseEntity.ok().body(meetingService.getFutureMeetings());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Server Error"));
        }
    }

    @GetMapping("/getPastMeetings")
    public ResponseEntity<Object> getPastMeetings()
    {
        try {
            return ResponseEntity.ok(meetingService.getPastMeetings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(""));
        }
    }

    @GetMapping("/getCurrentMeetings")
    public ResponseEntity<Object> getCurrentMeetings()
    {
        try {
            return ResponseEntity.ok(meetingService.getCurrentMeetings());
        }
        catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Server error"));
        }

    }

    @DeleteMapping("/deleteMeeting/{id}")
    public ResponseEntity<Object> deleteMeeting(@PathVariable("id") String id) {

        return ResponseEntity.ok(new ResponseMessage(meetingService.deleteMeetingById(id)));
    }

    @PostMapping("/updateMeeting/{type}/{id}")
    public ResponseEntity<Object> updateMeeting(@PathVariable("id") String meetingId , @PathVariable("type") String type, @RequestBody MeetingDTO meetingDTO) {
        try {
            String response = meetingService.updateMeeting(meetingDTO , meetingId,type);
            return ResponseEntity.ok(new ResponseMessage(response));
        }
        catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Failed to update meeting"));
        }
    }

    @PutMapping("/markMeetingAttendance")
    public  ResponseEntity<Object> markMeetingAttendance(@RequestBody MeetingAttendanceDTO attendanceDTO){
        return ResponseEntity.ok(new ResponseMessage(meetingService.markMeetingAttendance(attendanceDTO.getMeetingId() , attendanceDTO.getAttendees())));
    }

    @GetMapping("/getAllMeetingAttendants/{meetingId}")
    public ResponseEntity<List<MeetingAttendantsDTO>> getAllMeetingAttendants(@PathVariable("meetingId") String meetingId)
    {
        return  ResponseEntity.ok(meetingService.getAllMeetingAttendants(meetingId));
    }

    @GetMapping("/getPastMeetingAttendants/{meetingId}")
    public ResponseEntity<List<MeetingAttendantsDTO>> getPastMeetingAttendants(@PathVariable("meetingId") String meetingId)
    {
        return  ResponseEntity.ok(meetingService.getPastMeetingAttendants(meetingId));
    }
    @GetMapping("/getAllMeetingsByFacultyId/{facultyId}")
    public ResponseEntity<FacultyViewDTO> getAllMeetingsByFacultyId(@PathVariable("facultyId") String facultyId){
        return  ResponseEntity.ok(meetingService.getAllMeetingsByFacultyId(facultyId));
    }
}
