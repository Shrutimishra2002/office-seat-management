package services;

import com.google.inject.ImplementedBy;
import com.smartcoin.db.services.ModelService;
import models.Seat;
import services.impl.SeatServiceImpl;

import java.util.List;

@ImplementedBy(SeatServiceImpl.class)
public interface SeatService extends ModelService<Long,Seat> {
    Seat createSeat(String location, Long officeId);
    Seat updateSeat(Long id,String status);
    boolean deleteSeat(Long id);
    List<Seat> getSeats(Long officeId);
    Seat requestSeat(Long id);
    List<Seat> allSeatsFromaSingleOffice(Long officeId);
    void assignSeat(Long seatId, Long officeId);
}


