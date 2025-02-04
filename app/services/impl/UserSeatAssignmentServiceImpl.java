package services.impl;

import com.smartcoin.db.services.ModelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import models.UserSeatAssignment;
import services.UserSeatAssignmentService;

import java.util.Date;
import java.util.List;

@Slf4j
public class UserSeatAssignmentServiceImpl extends ModelServiceImpl<Long, UserSeatAssignment> implements UserSeatAssignmentService {

    @Override
    public UserSeatAssignment assignSeat(Long userId, Long seatId) {
        // Check if user already has a seat assigned
        UserSeatAssignment existingAssignment = getFinder().where().eq("USER_ID", userId).findUnique();
        if (existingAssignment != null) {
            return null; // User already has a seat assigned
        }

        UserSeatAssignment assignment = new UserSeatAssignment();
        assignment.setUserId(userId);
        assignment.setSeatId(seatId);
        assignment.setAssignedAt(new Date());
        assignment.save(); // Save the assignment to the database
        return assignment;
    }

    @Override
    public boolean hasAssignedSeat(Long userId) {
        // Check if the user already has an assigned seat
        UserSeatAssignment existingAssignment = getFinder().where().eq("USER_ID", userId).findUnique();
        return existingAssignment != null;
    }

    @Override
    public List<UserSeatAssignment> getUserAssignments(Long userId) {
        // Get all seat assignments for the user
        return getFinder().where().eq("USER_ID", userId).findList();
    }

    @Override
    public List<UserSeatAssignment> getAllAssignments() {
        // Get all seat assignments
        return getFinder().all();
    }

    @Override
    public void removeAssignment(Long userId) {
        // Remove the seat assignment for the user
        UserSeatAssignment existingAssignment = getFinder().where().eq("USER_ID", userId).findUnique();
        if (existingAssignment == null) {
            throw new RuntimeException("No Such user ");
        }
        existingAssignment.delete();
    }

    @Override
    public Long getAssignedSeat(Long userId) {
        UserSeatAssignment userAssignment = getFinder().where().eq("USER_ID", userId).findUnique();

        if (userAssignment != null) {
            return userAssignment.getSeatId();
        } else {
            throw new RuntimeException("No Such user ");
        }
    }
}
