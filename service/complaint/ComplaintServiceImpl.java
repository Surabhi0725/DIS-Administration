package sgsits.cse.dis.administration.service.complaint;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sgsits.cse.dis.administration.constants.ComplaintConstants;
import sgsits.cse.dis.administration.constants.SecurityConstants;
import sgsits.cse.dis.administration.dto.response.*;
import sgsits.cse.dis.administration.entity.ComplaintEntity;
import sgsits.cse.dis.administration.enums.ComplaintStatus;
import sgsits.cse.dis.administration.enums.ComplaintType;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.ResourceRequestNotFoundException;
import sgsits.cse.dis.administration.feignClient.InfrastructureClient;
import sgsits.cse.dis.administration.feignClient.UserClient;
import sgsits.cse.dis.administration.mapper.ComplaintMapper;
import sgsits.cse.dis.administration.repo.ComplaintRepository;

/**
 * The type  complaint service.
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {

  /**
   * The  complaint repository.
   */
  @Autowired
  private ComplaintRepository complaintRepository;
  @Autowired
  private ComplaintMapper complaintMapper;

  /**
   * The Infrastructure client.
   */
  @Autowired
  private InfrastructureClient infrastructureClient;

  @Autowired
  private UserClient userClient;

  @Override
  public List<ComplaintEntity> addMultipleComplaint(
      List<ComplaintEntity> complaintList,
      String userId
  ) throws ConflictException {
    for (ComplaintEntity complaint : complaintList) {
      if (StringUtils.isNotBlank(complaint.getAgainstUserId())) {
        validateUserIdIsPresent(complaint.getAgainstUserId());
      }
      complaint.setCreatedBy(userId);
      complaint.setLastModifiedBy(userId);
      complaint.setComplaintStatus(ComplaintStatus.PENDING);
    }
    return (List<ComplaintEntity>) complaintRepository.saveAll(complaintList);
  }

  /**
   * Gets complaint by id.
   *
   * @param complaintId the complaint id
   * @return the complaint by id
   */
  private ComplaintEntity getComplaintById(String complaintId) {
    return complaintRepository.findById(complaintId)
        .orElseThrow((() -> new ResourceRequestNotFoundException(
            ComplaintConstants.COMPLAINT_NOT_FOUND)));
  }

  @Override
  public ComplaintEntity assignAUserToComplaint(
      String complaintId,
      Set<String> assigneeIds,
      String assignedBy
  ) throws ConflictException {
    ComplaintEntity complaint = getComplaintById(complaintId);
    if (complaint.getComplaintStatus() == ComplaintStatus.PENDING
        || complaint.getComplaintStatus() == ComplaintStatus.IN_PROGRESS) {
      for (String assigneeId : assigneeIds) {
        validateUserIdIsPresent(assigneeId);
      }
      complaint.getAssignedUserIds().addAll(assigneeIds);
      complaint.setComplaintStatus(ComplaintStatus.IN_PROGRESS);
      complaint.setLastModifiedBy(assignedBy);
      return complaintRepository.save(complaint);
    }
    throw new ConflictException(
        String.format(
            ComplaintConstants.COMPLAINT_ALREADY_CLOSED,
            complaint.getComplaintStatus().getComplaintStatus(),
            complaint.getClosedRemarks()
        )
    );
  }

  private void validateUserIdIsPresent(String userId) throws ConflictException {
    String username = userClient.getUserIdByUserName(userId);
    if (StringUtils.isBlank(username)) {
      throw new ConflictException("User id: " + userId + " not found");
    }
  }

  @Override
  public ComplaintEntity resolveComplaint(
      String complaintId,
      String resolvedBy,
      String remarks
  ) throws ConflictException {
    ComplaintEntity complaint = getComplaintById(complaintId);
//    if (!complaint.getAssignedUserIds().contains(resolvedBy)) {
//      throw new ConflictException(ComplaintConstants.NEED_PERMISSION);
//    }
    if (complaint.getComplaintStatus() == ComplaintStatus.PENDING
        || complaint.getComplaintStatus() == ComplaintStatus.IN_PROGRESS) {
      complaint.setClosedBy(resolvedBy);
      complaint.setClosedRemarks(remarks);
      complaint.setClosedDate(new Date());
      complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
      complaint.setLastModifiedBy(resolvedBy);
      return complaintRepository.save(complaint);
    }
    throw new ConflictException(
        String.format(
            ComplaintConstants.COMPLAINT_ALREADY_CLOSED,
            complaint.getComplaintStatus().getComplaintStatus(),
            complaint.getClosedRemarks()
        )
    );
  }

  @Override
  public ComplaintEntity rejectComplaint(
      String complaintId,
      String rejectedBy,
      String remarks
  ) throws ConflictException {
    ComplaintEntity complaint = getComplaintById(complaintId);
//    if (!complaint.getAssignedUserIds().contains(rejectedBy)) {
//      throw new ConflictException(ComplaintConstants.NEED_PERMISSION);
//    }
    if (complaint.getComplaintStatus() == ComplaintStatus.PENDING
        || complaint.getComplaintStatus() == ComplaintStatus.IN_PROGRESS) {
      validateUserIdIsPresent(rejectedBy);
      complaint.setClosedBy(rejectedBy);
      complaint.setClosedRemarks(remarks);
      complaint.setClosedDate(new Date());
      complaint.setLastModifiedBy(rejectedBy);
      complaint.setComplaintStatus(ComplaintStatus.REJECTED);
      return complaintRepository.save(complaint);
    }
    throw new ConflictException(
        String.format(
            ComplaintConstants.COMPLAINT_ALREADY_CLOSED,
            complaint.getComplaintStatus().getComplaintStatus(),
            complaint.getClosedRemarks()
        )
    );
  }

  @Override
  public List<ComplaintResponseDto> getComplaints(
      String complaintStatus,
      String complaintType
  ) {
//    if (complaintStatus.equalsIgnoreCase("ALL") && complaintType.equalsIgnoreCase("ALL")) {
//      return complaintRepository.findAll();
//    }
    ComplaintType complaintTypeEnum = ComplaintType.valueOf(complaintType);
    ComplaintStatus complaintStatusEnum = ComplaintStatus.valueOf(complaintStatus);
    if (complaintType.equalsIgnoreCase("ALL")) {
      return complaintRepository.findByComplaintStatus(complaintStatusEnum).stream()
              .map(complaintMapper::toDto)
              .collect(Collectors.toList());
    }
    if (complaintStatus.equalsIgnoreCase("ALL")) {
      return complaintRepository.findByComplaintType(complaintTypeEnum).stream()
              .map(complaintMapper::toDto)
              .collect(Collectors.toList());
    }
    if(complaintType.equals("STUDENT")){
      List<ComplaintResponseDto> complaintResponseDtoList = complaintRepository.findByComplaintStatusAndComplaintType(complaintStatusEnum, complaintTypeEnum).stream()
              .map(complaintMapper::toDto)
              .collect(Collectors.toList());
      for (ComplaintResponseDto complaintResponseDto : complaintResponseDtoList) {
        StudentDto studentDto = userClient.getNameByUserId(complaintResponseDto.getAgainstUserId());
        complaintResponseDto.setAgainstUserName(studentDto.getId());
        complaintResponseDto.setAgainstUserFullName(studentDto.getName());
      }
      return complaintResponseDtoList;
    }
    return complaintRepository.findByComplaintStatusAndComplaintType(complaintStatusEnum,
        complaintTypeEnum).stream()
            .map(complaintMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<ComplaintEntity> getAllComplaints() {
    return (List<ComplaintEntity>) complaintRepository.findAll();
  }

  @Override
  public List<ComplaintResponseDto> getComplaintsCreatedByUserIdAndComplaintType(String id, String complaintType) {
    if (complaintType.equalsIgnoreCase("ALL")) {
      return complaintRepository.findByCreatedBy(id).stream()
              .map(complaintMapper::toDto)
              .collect(Collectors.toList());
    }
    ComplaintType complaintTypeEnum = ComplaintType.valueOf(complaintType);
    if(complaintType.equals("STUDENT")){
      List<ComplaintResponseDto> complaintResponseDtoList = complaintRepository.findByCreatedByAndComplaintType(id, complaintTypeEnum).stream()
              .map(complaintMapper::toDto)
              .collect(Collectors.toList());
      for (ComplaintResponseDto complaintResponseDto : complaintResponseDtoList) {
        StudentDto studentDto = userClient.getNameByUserId(complaintResponseDto.getAgainstUserId());
        complaintResponseDto.setAgainstUserName(studentDto.getId());
        complaintResponseDto.setAgainstUserFullName(studentDto.getName());
      }
      return complaintResponseDtoList;
    }
    return complaintRepository.findByCreatedByAndComplaintType(id, complaintTypeEnum).stream()
            .map(complaintMapper::toDto)
            .collect(Collectors.toList());
  }

  @Override
  public List<StudentDto> getStudentList() {
    return userClient.getStudentUserNameList();
  }

  @Override
  public List<StudentDto> getStudentFullNameList() {
    return userClient.getStudentFullNameList();
  }

  @Override
  public ComplaintCountDto getComplaintCounts(String userType, String userId) {
    if(userType.equals(SecurityConstants.HEAD)){
      return new ComplaintCountDto(complaintRepository.count(),
              complaintRepository.countByComplaintStatus(ComplaintStatus.PENDING),
              complaintRepository.countByComplaintStatus(ComplaintStatus.RESOLVED),
              complaintRepository.countByComplaintStatus(ComplaintStatus.REJECTED));
    }else if(userType.equals(SecurityConstants.FACULTY)){
      return new ComplaintCountDto(complaintRepository.countByCreatedBy(userId),
              complaintRepository.countByCreatedByAndComplaintStatus(userId,ComplaintStatus.PENDING),
              complaintRepository.countByCreatedByAndComplaintStatus(userId,ComplaintStatus.RESOLVED),
              complaintRepository.countByCreatedByAndComplaintStatus(userId,ComplaintStatus.REJECTED));
    }
    return null;
  }

  @Override
  public List<ComplaintTypePendingStateDto> getComplaintPendingStates(String userType, String userId) {
    List<ComplaintTypePendingStateDto> complaintTypePendingStateDtoList = new ArrayList<>();
    List<ComplaintEntity> complaintEntityList = new ArrayList<>();

    if(userType.equals(SecurityConstants.HEAD)) {
      complaintEntityList = complaintRepository.findByComplaintStatus(ComplaintStatus.PENDING);
    }
    else if(userType.equals(SecurityConstants.FACULTY)) {
      complaintEntityList = complaintRepository.findByCreatedByAndComplaintStatus(userId, ComplaintStatus.PENDING);
    }

      Set<ComplaintType> actualPendingStateSet = new HashSet<>();
      for(ComplaintEntity complaintEntity : complaintEntityList){
        actualPendingStateSet.add(complaintEntity.getComplaintType());
      }
      for(ComplaintType complaintType : ComplaintType.values()){
        if(actualPendingStateSet.contains(complaintType)){
          ComplaintTypePendingStateDto complaintTypePendingStateDto = new ComplaintTypePendingStateDto();
          complaintTypePendingStateDto.setComplaintType(complaintType);
          complaintTypePendingStateDto.setIsPending(true);
          complaintTypePendingStateDtoList.add(complaintTypePendingStateDto);
        }
      }
      return complaintTypePendingStateDtoList;
  }

  @Override
  public List<ComplaintEntity> getComplaintsAssignedToUserId(String userId)
      throws ConflictException {
    Set<ComplaintEntity> complaintEntities = new HashSet<>();
    try {
      validateUserIdIsPresent(userId);
      List<String> location = infrastructureClient.findInchargeOf(userId);
      complaintEntities.addAll(complaintRepository.findByLocationIn(location));
      complaintEntities.addAll(
          complaintRepository.findByAssignedUserIdsIgnoreCaseContaining(userId));
    } catch (Exception exception) {
      throw new ConflictException("User is not incharge of any location");
    }
    return new ArrayList<>(complaintEntities);
  }

  @Override
  public List<ComplaintEntity> getComplaintsCreatedByUserId(String userId)
      throws ConflictException {
    validateUserIdIsPresent(userId);
    return complaintRepository.findByCreatedBy(userId);
  }

  @Override
  public ComplaintReportResponseDto getComplaintReport(String userId) throws ConflictException {
    validateUserIdIsPresent(userId);
    ComplaintReportResponseDto complaintReportResponseDto = new ComplaintReportResponseDto();
    complaintReportResponseDto.setAll(
        new StatusAndType(new ConcurrentHashMap<>(), new ConcurrentHashMap<>()));
    complaintReportResponseDto.setMy(
        new StatusAndType(new ConcurrentHashMap<>(), new ConcurrentHashMap<>()));
    for (ComplaintEntity complaintEntity : complaintRepository.findAll()) {
      for (ComplaintStatus complaintStatus : ComplaintStatus.values()) {
        if (complaintEntity.getComplaintStatus() == complaintStatus) {
          complaintReportResponseDto.getAll().getStatus().put(
              complaintStatus,
              complaintReportResponseDto.getAll().getStatus().getOrDefault(
                  complaintStatus, 0
              ) + 1
          );
          if (complaintEntity.getAssignedUserIds().contains(userId)
              || complaintEntity.getCreatedBy().equalsIgnoreCase(userId)) {
            complaintReportResponseDto.getMy().getStatus().put(
                complaintStatus,
                complaintReportResponseDto.getMy().getStatus().getOrDefault(
                    complaintStatus, 0
                ) + 1
            );
          }
        }
      }
      for (ComplaintType complaintType : ComplaintType.values()) {
        if (complaintEntity.getComplaintType() == complaintType) {
          complaintReportResponseDto.getAll().getType().put(
              complaintType,
              complaintReportResponseDto.getAll().getType().getOrDefault(
                  complaintType, 0
              ) + 1
          );
          if (complaintEntity.getAssignedUserIds().contains(userId)
              || complaintEntity.getCreatedBy().equalsIgnoreCase(userId)) {
            complaintReportResponseDto.getMy().getType().put(
                complaintType,
                complaintReportResponseDto.getMy().getType().getOrDefault(
                    complaintType, 0
                ) + 1
            );
          }
        }
      }
    }
    return complaintReportResponseDto;
  }

}
