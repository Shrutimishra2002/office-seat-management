package models;

import com.avaje.ebean.Model;
import com.smartcoin.db.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import play.data.validation.Constraints;

@Entity
@Data
@NoArgsConstructor
public class Office extends BaseModel {

    @Constraints.Required
    private String name;

    @Constraints.Required
    private int rowsLength; // Number of rows in the seat matrix

    @Constraints.Required
    private int columnsLength; // Number of columns in the seat matrix

}
