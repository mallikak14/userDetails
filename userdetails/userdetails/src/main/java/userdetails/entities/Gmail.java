package userdetails.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="mails")
public class Gmail {
    @Id
    @Column(name="GID")
    int gmailid;
    @Column(name="gmail")
    String gmail;
    @ManyToOne
    @JoinColumn(name="USERID")
   private User user;
   
    // @OneToMany(mappedBy = "gmailids")
    // private Set<User> userSet;
   

   
}
