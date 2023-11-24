package sgsits.cse.dis.administration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgsits.cse.dis.administration.enums.ComplaintType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintTypePendingStateDto {
    @Enumerated(value = EnumType.STRING)
    private ComplaintType complaintType;

    private Boolean isPending;
}
