package sgsits.cse.dis.administration.models;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meetings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Meetings {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy="org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "meeting_id", nullable = false, unique = true)
    private String meetingId;

    @Column(name = "meeting_date", nullable = false)
    private Date meetingDate;

    @Column(name = "meeting_time", nullable = false)
    private Date meetingTime;

    @Column(name = "meeting_end_time", nullable = false)
    private Date meetingEndTime;

    @Column(name = "meeting_objective", nullable = false)
    private String meetingObjective;

    @Column(name = "meeting_venue", nullable = false)
    private String meetingVenue;

    @Column(name = "meeting_host_id", nullable = false)
    private String meetingHostId;

    @Column(name = "meeting_summary")
    private  String meetingSummary;

    @Column(name="meeting_duration")
    private  String meetingDuration;


}
