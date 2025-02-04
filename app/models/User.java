
package models;

import com.avaje.ebean.Model;
import com.smartcoin.db.models.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "user")
public class User extends BaseModel {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String email;
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    // PrePersist: Sets the createdAt and updatedAt timestamps before inserting a new record
    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
    }

    // PreUpdate: Updates the updatedAt timestamp before updating a record
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

}
