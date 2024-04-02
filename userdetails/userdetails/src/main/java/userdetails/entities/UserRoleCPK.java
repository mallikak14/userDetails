package userdetails.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Embeddable
public class UserRoleCPK implements Serializable{

    @Column(name="USERID")
    int userId;
    @Column(name="ROLEID")
    int roleID;
    
}
