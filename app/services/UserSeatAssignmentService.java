package services;

import com.smartcoin.db.services.ModelService;
import com.google.inject.ImplementedBy;
import models.UserSeatAssignment;
import services.impl.UserSeatAssignmentServiceImpl;

import java.util.List;

@ImplementedBy(UserSeatAssignmentServiceImpl.class)
public interface UserSeatAssignmentService extends ModelService <Long, models.UserSeatAssignment> {
    UserSeatAssignment assignSeat(Long userId, Long seatId);
    boolean hasAssignedSeat(Long userId);
    List<UserSeatAssignment> getUserAssignments(Long userId);
    List<UserSeatAssignment> getAllAssignments();
    void removeAssignment(Long userId);

    Long getAssignedSeat(Long userId);
}
