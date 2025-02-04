
package models;

import com.smartcoin.db.models.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_seat_assignment")
public class UserSeatAssignment extends BaseModel {
    private Long userId;
    private Long seatId;
    private Date assignedAt;
    public static String USER_ID = "user_id";
    public static String SEAT_ID = "seat_id";
}
