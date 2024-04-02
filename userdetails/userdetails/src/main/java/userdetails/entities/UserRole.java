package userdetails.entities;

import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserRole {
    @EmbeddedId
    public UserRole user_role;
    
}
