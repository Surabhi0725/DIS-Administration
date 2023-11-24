package sgsits.cse.dis.administration.dto.request;


import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class MeetingAttendeeDTO {

    private String id;
    private String userId;
    private String name;
    private String nameAcronym;
    private String profilePicture;
    private String currentDesignation;
    private String email;
    private Long mobileNo;
    private Long alternateMobileNo;
    private String userName;
    private Integer isPresent;

}
