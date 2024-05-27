package userdetails.entities;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GID")
    int gmailid;
    @Column(name="GMAIL")
    String gmail;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USERID")
    private User user;

    public Gmail( String gmail) {
        this.gmail = gmail;
    }
   
    // @OneToMany(mappedBy = "gmailids")
    // private Set<User> userSet;
   

   
}
