package userdetails.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name="ROLES")
public class Role {
    @Id 
    @Column(name="ROLEID")
    @NotNull(message = "roleid should be provided")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roleId;
    
    @Column(name="ROLENAME")
    String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "roles")
    Set<User> user=new HashSet<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "role")
    private User userforonetoone;
 
   
}
