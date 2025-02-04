
package models;

import com.avaje.ebean.Model;
import com.smartcoin.db.models.BaseModel;
import lombok.Data;

import java.util.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "seat")
public class Seat extends BaseModel {
    private Long officeID;
    private Integer rowInNumber;
    private Integer colInNumber;
    private String status;

    public static String OFFICE_ID = "office_id";
    public static String ROW_IN_NUMBER = "row_in_number";
    public static String COL_IN_NUMBER = "col_in_number";

}
