package sgsits.cse.dis.administration.service.meeting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgsits.cse.dis.administration.dto.request.MeetingAttendeeDTO;
import sgsits.cse.dis.administration.dto.request.MeetingDTO;
import sgsits.cse.dis.administration.dto.response.*;
import sgsits.cse.dis.administration.feignClient.UserClient;
import sgsits.cse.dis.administration.models.MeetingAttendants;
import sgsits.cse.dis.administration.models.Meetings;
import sgsits.cse.dis.administration.repo.MeetingAttendantsRepo;
import sgsits.cse.dis.administration.repo.MeetingsRepo;

import javax.management.InstanceAlreadyExistsException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final String TIME_ZONE = "+05:30[Asia/Kolkata]";
    @Autowired
    private MeetingsRepo meetingsRepo;

    @Autowired
    private MeetingAttendantsRepo meetingAttendantsRepo;

    @Autowired
    private UserClient userClient;

    private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
    @Override
    public String createMeeting(MeetingDTO meetingDTO) {
        try {
           // System.out.println(meetingDTO.getHostId());
            List<Meetings> meetingsList = meetingsRepo.findAll();

            String id = userClient.getUserIdByUserName(meetingDTO.getHostId());
            System.out.println(id);
            Meetings meetings = Meetings.builder()
                    .meetingDate(sdf.parse(meetingDTO.getMeetingDate()))
                    .meetingObjective(meetingDTO.getMeetingObjective())
                    .meetingHostId(id)
                    .meetingVenue(meetingDTO.getMeetingVenue())
                    .meetingTime(sdf2.parse(meetingDTO.getMeetingTime()))
                    .meetingSummary(meetingDTO.getMeetingSummary())
                    .meetingEndTime(sdf2.parse(meetingDTO.getMeetingEndTime()))
                    .meetingDuration(meetingDTO.getMeetingDuration())
                    .build();
            System.out.println(meetings);
            Meetings savedMeeting = meetingsRepo.save(meetings);

            if(!savedMeeting.equals(null)) {
                List<String> meetingAttendantsList = meetingDTO.getMeetingAttendants();
                for (String meetingAttendant : meetingAttendantsList) {
                    MeetingAttendants meetingAttendants = MeetingAttendants.builder()
                            .attendantId(meetingAttendant)
                            .meetingId(savedMeeting.getMeetingId())
                            .build();
                    meetingAttendantsRepo.save(meetingAttendants);
                }
            }

            for(Meetings meeting : meetingsList){
                if(sdf.parse(sdf.format(meeting.getMeetingDate())).equals(sdf.parse(meetingDTO.getMeetingDate()))){
                    return "There is another meeting set for the same date";
                }
            }
            return "Meeting created successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed to create meeting";
    }

    @Override
    public String updateMeeting(MeetingDTO meetingDTO , String meetingId, String type) {
        try {
            System.out.println("in updateMeeting..");
            String id = userClient.getUserIdByUserName(meetingDTO.getHostId());
            Meetings meetingEntity = meetingsRepo.findById(meetingId).get();
            meetingEntity.setMeetingId(meetingId);
            meetingEntity.setMeetingDate(sdf.parse(meetingDTO.getMeetingDate()));
            meetingEntity.setMeetingDuration(meetingDTO.getMeetingDuration());
            meetingEntity.setMeetingHostId(id);
            meetingEntity.setMeetingTime(sdf2.parse(meetingDTO.getMeetingTime()));
            meetingEntity.setMeetingVenue(meetingDTO.getMeetingVenue());
            meetingEntity.setMeetingEndTime(sdf2.parse(meetingDTO.getMeetingEndTime()));
            meetingEntity.setMeetingSummary(meetingDTO.getMeetingSummary());
            meetingEntity.setMeetingObjective(meetingDTO.getMeetingObjective());

            // attendant thing not working right now
            if(!meetingsRepo.save(meetingEntity).equals(null)) {
                List<String> meetingAttendantsIds = meetingDTO.getMeetingAttendants();
                List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetingId);
                List<MeetingAttendants> updatedMeetingAttendees = new ArrayList<>();

//                if(type.equals("updateSummary")){
//                  //  for(String meetingAttendantId : meetingAttendantsIds)
//                    //{
//                        for ( MeetingAttendants meetingAttendants : meetingAttendantsList) {
//                          //  if(meetingAttendants.getAttendantId() == meetingAttendantId){
//                                MeetingAttendants meetingAttendant = MeetingAttendants.builder()
//                                        .attendantId(meetingAttendants.getAttendantId())
//                                        .meetingId(meetingId)
//                                        .isPresent(meetingAttendants.getIsPresent())
//                                        .build();
//                                updatedMeetingAttendees.add(meetingAttendant);
//                          //  }
//                        }
//                        //
//                    //}
//                }
                if(type.equals("updateMeeting")){
                    for ( MeetingAttendants meetingAttendants : meetingAttendantsList){
                        meetingAttendantsRepo.deleteById(meetingAttendants.getId());
                    }
                    for (String meetingAttendantId : meetingAttendantsIds) {
                        System.out.println("***"+meetingAttendantId);
                        MeetingAttendants meetingAttendant = MeetingAttendants.builder()
                                .attendantId(meetingAttendantId)
                                .meetingId(meetingId)
                                .build();
                        updatedMeetingAttendees.add(meetingAttendant);
                        //meetingAttendantsRepo.save(meetingAttendant);
                    }
                }
                meetingAttendantsRepo.saveAll(updatedMeetingAttendees);
//                for(String meetingAttendantId : meetingAttendantsIds)
//                {
//                    for ( MeetingAttendants meetingAttendants : meetingAttendantsList) {
//                        if(meetingAttendants.getAttendantId() == meetingAttendantId){
//                            MeetingAttendants meetingAttendant = MeetingAttendants.builder()
//                                    .attendantId(meetingAttendantId)
//                                    .meetingId(meetingId)
//                                    .isPresent(meetingAttendants.getIsPresent())
//                                    .build();
//                            updatedMeetingAttendees.add(meetingAttendant);
//                        }
//                    }
//                    //meetingAttendantsRepo.deleteById(meetingAttendants.getId());
//                }

            }
            return "Meeting updated successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed to update meeting";
    }
    @Override
    public List<HODMeetingResponseDTO> getAllMeetings() {

        try{

            List<Meetings> meetingsList = meetingsRepo.findAll();
            List<HODMeetingResponseDTO> hodMeetingResponseDTOList =  new ArrayList<>();

            for(Meetings meetings : meetingsList)
            {
                List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetings.getMeetingId());
                List<String> meetingAttendantId = new ArrayList<>();
                for(MeetingAttendants meetingAttendants : meetingAttendantsList)
                {
                    meetingAttendantId.add(meetingAttendants.getAttendantId());
                }

                HODMeetingResponseDTO hodMeetingResponseDTO = HODMeetingResponseDTO.builder().
                        meetingDate(sdf.format(meetings.getMeetingDate())).
                        meetingTime(sdf2.format(meetings.getMeetingTime())).
                        meetingEndTime(sdf2.format(meetings.getMeetingEndTime())).
                        meetingVenue(meetings.getMeetingVenue()).
                        meetingObjective(meetings.getMeetingObjective()).
                        meetingAttendants(meetingAttendantId).
                        meetingSummary(meetings.getMeetingSummary()).
                        meetingDuration(meetings.getMeetingDuration()).
                        meetingId(meetings.getMeetingId()).
                        build();
                hodMeetingResponseDTOList.add(hodMeetingResponseDTO);
            }
            return  hodMeetingResponseDTOList;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<HODMeetingResponseDTO> getFutureMeetings() {
        try{

            List<Meetings> meetingsList = meetingsRepo.findAll();
            List<HODMeetingResponseDTO> hodMeetingResponseDTOList =  new ArrayList<>();
            for(Meetings meetings : meetingsList)
            {
                System.out.println("in getFutureMeetings");
                List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetings.getMeetingId());
                List<String> meetingAttendantId = new ArrayList<>();
                for(MeetingAttendants meetingAttendants : meetingAttendantsList)
                {
                    meetingAttendantId.add(meetingAttendants.getAttendantId());
                }
                System.out.println(sdf.format(meetings.getMeetingDate()));
                HODMeetingResponseDTO hodMeetingResponseDTO = HODMeetingResponseDTO.builder().
                        meetingDate(sdf.format(meetings.getMeetingDate())).
                        meetingTime(sdf2.format(meetings.getMeetingTime())).
                        meetingEndTime(sdf2.format(meetings.getMeetingEndTime())).
                        meetingVenue(meetings.getMeetingVenue()).
                        meetingObjective(meetings.getMeetingObjective()).
                        meetingAttendants(meetingAttendantId).
                        meetingDuration(meetings.getMeetingDuration()).
                        meetingSummary(meetings.getMeetingSummary()).
                        meetingId(meetings.getMeetingId())
                        .build();
                String time = hodMeetingResponseDTO.getMeetingDate() + " " + hodMeetingResponseDTO.getMeetingTime();
                System.out.println(time);
                if(isFutureMeeting(time)) {
                    hodMeetingResponseDTOList.add(hodMeetingResponseDTO);
                }

            }

            return  hodMeetingResponseDTOList;

        }
        catch (Exception e)
        {

            e.printStackTrace();
        }

        return   new ArrayList<>();
    }

    @Override
    public List<HODMeetingResponseDTO> getPastMeetings() {
        try {
            List<Meetings> meetingsList = meetingsRepo.findAll();
            List<HODMeetingResponseDTO> hodMeetingResponseDTOList =  new ArrayList<>();
            for(Meetings meetings : meetingsList)
            {
                System.out.println("in getPastMeetings");
                List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetings.getMeetingId());
                List<String> meetingAttendantId = new ArrayList<>();
                List<MeetingAttendantsDTO> meetingAttendantsDTOList = new ArrayList<>();
                for(MeetingAttendants meetingAttendant : meetingAttendantsList)
                {

                    //originally uncommented exception not handeled
//                    if(meetingAttendants.getIsPresent() == 1) {
//                        StudentDto studentDto = new StudentDto();
//                        studentDto = userClient.getNameByUserId(meetingAttendants.getAttendantId());
//                        meetingAttendantId.add(studentDto.getName());
//                    }

                    if(meetingAttendant.getIsPresent() == 1) {
                        String username = userClient.getByUserName(meetingAttendant.getAttendantId());
                        MeetingAttendantsDTO meetingAttendantsDTO = MeetingAttendantsDTO.builder()
                                .meetingAttendants(meetingAttendant)
                                .name(username)
                                .build();
                        meetingAttendantsDTOList.add(meetingAttendantsDTO);
                        meetingAttendantId.add(meetingAttendant.getAttendantId());
                    }
                }

                HODMeetingResponseDTO hodMeetingResponseDTO = HODMeetingResponseDTO.builder().
                        meetingDate(sdf.format(meetings.getMeetingDate())).
                        meetingTime(sdf2.format(meetings.getMeetingTime())).
                        meetingEndTime(sdf2.format(meetings.getMeetingEndTime())).
                        meetingVenue(meetings.getMeetingVenue()).
                        meetingObjective(meetings.getMeetingObjective()).
                        meetingAttendants(meetingAttendantId).
                        meetingDuration(meetings.getMeetingDuration()).
                        meetingSummary(meetings.getMeetingSummary()).
                        meetingId(meetings.getMeetingId())
                        .build();

                String time = hodMeetingResponseDTO.getMeetingDate() + " " + hodMeetingResponseDTO.getMeetingEndTime();
                if(isPastMeeting(time)) {
                    hodMeetingResponseDTOList.add(hodMeetingResponseDTO);
                }
            }
            return  hodMeetingResponseDTOList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<HODMeetingResponseDTO> getCurrentMeetings() {
        try{
            List<Meetings> meetingsList = meetingsRepo.findAll();
            List<HODMeetingResponseDTO> hodMeetingResponseDTOList =  new ArrayList<>();
            for(Meetings meetings : meetingsList)
            {
                System.out.println("in getCurrentMeetings");
                List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetings.getMeetingId());
                List<String> meetingAttendantId = new ArrayList<>();
                for(MeetingAttendants meetingAttendants : meetingAttendantsList)
                {
                    meetingAttendantId.add(meetingAttendants.getAttendantId());
                }

                HODMeetingResponseDTO hodMeetingResponseDTO = HODMeetingResponseDTO.builder().
                        meetingDate(sdf.format(meetings.getMeetingDate())).
                        meetingTime(sdf2.format(meetings.getMeetingTime())).
                        meetingEndTime(sdf2.format(meetings.getMeetingEndTime())).
                        meetingVenue(meetings.getMeetingVenue()).
                        meetingObjective(meetings.getMeetingObjective()).
                        meetingAttendants(meetingAttendantId).
                        meetingDuration(meetings.getMeetingDuration()).
                        meetingSummary(meetings.getMeetingSummary()).
                        meetingId(meetings.getMeetingId())
                        .build();

                String time = hodMeetingResponseDTO.getMeetingDate() + " " + hodMeetingResponseDTO.getMeetingTime();
                String endTime = hodMeetingResponseDTO.getMeetingDate() + " " + hodMeetingResponseDTO.getMeetingEndTime();
                if(isCurrentMeeting(time, endTime)) {
                    hodMeetingResponseDTOList.add(hodMeetingResponseDTO);
                }
            }
            return  hodMeetingResponseDTOList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }




    @Override
    public FacultyViewDTO getAllMeetingsByFacultyId(String attendantId) {
        try{
            //List<Meetings> meetingsList = meetingsRepo.findAll();
            List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByAttendantId(attendantId);

            List<MeetingAndAttendanceDTO> pastMeetingsList = new ArrayList<>();
            List<MeetingAndAttendanceDTO> futureMeetingsList = new ArrayList<>();
            List<MeetingAndAttendanceDTO> currentMeetingsList = new ArrayList<>();

            for(MeetingAttendants meetingAttendants : meetingAttendantsList)
            {
                //incorrect
                if(isPastMeeting(meetingAttendants.getMeetingId()))
                {
                    pastMeetingsList.add(new MeetingAndAttendanceDTO(meetingsRepo.findById(meetingAttendants.getMeetingId()).get() , meetingAttendants.getIsPresent()));
                }

                else{
                    currentMeetingsList.add(new MeetingAndAttendanceDTO(meetingsRepo.findById(meetingAttendants.getMeetingId()).get() , meetingAttendants.getIsPresent()));
                }



            }

            return  new FacultyViewDTO(pastMeetingsList , currentMeetingsList , futureMeetingsList) ;



        }

        catch (Exception e)
        {

            e.printStackTrace();
        }

        return  null;
    }


    @Override
    public String deleteMeetingById(String id) {

        try {
            meetingsRepo.deleteById(id);
            return "Meeting cancelled successfully";
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "Meeting cancellation failed!";

    }
    @Override
    public boolean isFutureMeeting(String time) {
        try {
            SimpleDateFormat sdf3  = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date mt = sdf3.parse(time);
            Date now = new Date();
            System.out.println(now);
            System.out.println(sdf3.format(now));
            if(mt.after(now)){
                System.out.println("future");
            }else{
                System.out.println("not future");
            }
            return mt.after(now);
//            Instant meetingInstant = ZonedDateTime.parse(time + TIME_ZONE).toInstant();
//            Instant currentInstant = Instant.now();
//            System.out.println(meetingInstant);
//            System.out.println(currentInstant);
//            System.out.println(meetingInstant.isAfter(currentInstant));
//            return meetingInstant.isAfter(currentInstant);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isPastMeeting(String time) throws ParseException {
        System.out.println("in past meeting check");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss");
//        Date meetingDateTime = sdf.parse(time);
//        Date currentTime = new Date();
//        System.out.println(currentTime+" : "+meetingDateTime);

        SimpleDateFormat sdf3  = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date mt = sdf3.parse(time);
            Date now = new Date();
            System.out.println(mt);
            System.out.println(sdf3.format(now));
            if(mt.before(now)){
                System.out.println("past");
            }else{
                System.out.println("not past");
            }
            return mt.before(now);
//        Instant meetingInstant = ZonedDateTime.parse(time + TIME_ZONE).toInstant();
//        System.out.println(meetingInstant);
//        Instant currentInstant = Instant.now();
//        System.out.println(currentInstant);
//        long hour = Long.parseLong(duration.split(":")[0]);
//        long minute = Long.parseLong(duration.split(":")[1]);
//        System.out.println(hour +" "+minute);
//        Instant meetingEndInstant = meetingInstant.plus(hour, ChronoUnit.HOURS).plus(minute, ChronoUnit.MINUTES);
//        return  meetingEndInstant.isBefore(currentInstant);
    }
    @Override
    public boolean isCurrentMeeting(String time, String endTime) {
        try{
            SimpleDateFormat sdf3  = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date start = sdf3.parse(time);
            Date end = sdf3.parse(endTime);
            Date now = new Date();
            System.out.println(now);
            System.out.println(sdf3.format(now)+" : "+start+" : "+end);
            if(now.before(end) && now.after(start)){
                System.out.println("current");
            }else{
                System.out.println("not current");
            }

            return (now.before(end) && now.after(start));

//            Instant meetingInstant = ZonedDateTime.parse(time + TIME_ZONE).toInstant();
//            Instant currentInstant = Instant.now();
//            long hour = Long.parseLong(duration.split(":")[0]);
//            long minute = Long.parseLong(duration.split(":")[1]);
//            Instant meetingEndInstant = meetingInstant.plus(hour, ChronoUnit.HOURS).plus(minute, ChronoUnit.MINUTES);
//            return (currentInstant.isAfter(meetingInstant) && currentInstant.isBefore(meetingEndInstant));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String markMeetingAttendance(String meetingId, MeetingAttendeeDTO[] attendees) {
        List<MeetingAttendants> meetingAttendants = new ArrayList<>();
        try {
            for (MeetingAttendeeDTO attendee : attendees) {
                System.out.println(meetingId+" : "+attendee.getUserId()+" : "+attendee.getIsPresent());
                MeetingAttendants meetingAttendant = meetingAttendantsRepo.findByMeetingIdAndAttendantId(meetingId, attendee.getUserId());
                meetingAttendant.setIsPresent(attendee.getIsPresent());
                meetingAttendants.add(meetingAttendant);
                //return "Attendance Marked for meetingId: " + meetingId + " for: " +attendantId;
            }
            meetingAttendantsRepo.saveAll(meetingAttendants);
            System.out.println("Attendance marked for the meeting.");
            return "Attendance marked for the meeting.";
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "Failed to mark attendance!";
    }

//    @Override
//    public List<String> getAllMeetingAttendantsNames(String meetingId) {
//        try {
//
//                List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetingId);
//                List<String> meetingAttendantsNameList = new ArrayList<>();
//                for(MeetingAttendants meetingAttendant : meetingAttendantsList)
//                {
//                    String username = userClient.getNameByUserId(meetingAttendant.getAttendantId());
//                    meetingAttendantsNameList.add(username);
//
//                }
//
//                return  meetingAttendantsNameList;
//
//        }
//
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return new ArrayList<>();
//    }

//    @Override
//    public List<String> getPastMeetingAttendantsNames(String meetingId) {
//        try {
//
//            List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetingId);
//            List<String> meetingAttendantsNameList = new ArrayList<>();
//            for(MeetingAttendants meetingAttendant : meetingAttendantsList)
//            {
//
//                if(meetingAttendant.getIsPresent() == 1) {
//                    String username = userClient.getNameByUserId(meetingAttendant.getAttendantId());
//                    meetingAttendantsNameList.add(username);
//                }
//
//            }
//
//            return  meetingAttendantsNameList;
//
//        }
//
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return new ArrayList<>();
//    }

    @Override
    public List<MeetingAttendantsDTO> getPastMeetingAttendants(String meetingId) {
        try {

            List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetingId);
           // List<MeetingAttendants> pastMeetingAttendantsList = new ArrayList<>();
            List<MeetingAttendantsDTO> meetingAttendantsDTOList = new ArrayList<>();

            for(MeetingAttendants meetingAttendant : meetingAttendantsList)
            {

                if(meetingAttendant.getIsPresent() == 1) {

                    String username = userClient.getByUserName(meetingAttendant.getAttendantId());
                    MeetingAttendantsDTO meetingAttendantsDTO = MeetingAttendantsDTO.builder()
                            .meetingAttendants(meetingAttendant)
                            .name(username)
                            .build();
                    meetingAttendantsDTOList.add(meetingAttendantsDTO);
                }

            }

            return  meetingAttendantsDTOList;

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<MeetingAttendantsDTO> getAllMeetingAttendants(String meetingId) {
        try {
            List<MeetingAttendants> meetingAttendantsList = meetingAttendantsRepo.findByMeetingId(meetingId);
            for(MeetingAttendants meetingAttendant : meetingAttendantsList)
                System.out.println(meetingAttendant);
            List<MeetingAttendantsDTO> meetingAttendantsDTOList = new ArrayList<>();
            for(MeetingAttendants meetingAttendant : meetingAttendantsList) {
                System.out.println("********");
                System.out.println(meetingAttendant.getAttendantId());

                String username = userClient.getByUserName(meetingAttendant.getAttendantId());
                System.out.println(username);
                MeetingAttendantsDTO meetingAttendantsDTO = MeetingAttendantsDTO.builder()
                        .meetingAttendants(meetingAttendant)
                        .name(username)
                        .build();
                meetingAttendantsDTOList.add(meetingAttendantsDTO);
            }
            return  meetingAttendantsDTOList;

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
