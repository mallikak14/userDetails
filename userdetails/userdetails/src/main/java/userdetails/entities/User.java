package userdetails.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @Column(name = "USERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;
    @Column(name = "USERNAME")
    @NotBlank(message = "username should be provided")
    String userName;
    @Column(name = "AGE")
    int age;
    @Column(name = "HEIGHT")
    @NotNull(message = "height should be provided")
    double height;
    @Column(name = "ISDELETED")
    @NotNull(message = "isdeleted should be provided")
    boolean isdeleted;
    @Column(name = "DOB")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    Date dob;
    @Column(name = "GMAIL")
    //@Email(message = "valid mail should be provided")
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Gmail> gmail;

    @Column(name = "MOBILE")
    @Digits(integer = 10, fraction = 0)
    long mobile;
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="USERID",referencedColumnName = "userId")
    private Role role; 

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//,mappedBy = "user")
    @JoinTable(name = "user_roles", 
    joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"), 
    inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
    private Set<Role> roles = new HashSet<Role>();

    public User(@NotBlank(message = "username should be provided") String userName, @Min(10) int age,
            @NotNull(message = "height should be provided") double height,
            @NotNull(message = "isdeleted should be provided") boolean isdeleted, Date dob,
            @Digits(integer = 10, fraction = 0) long mobile)  {
        this.userName = userName;
        this.age = age;
        this.height = height;
        this.isdeleted = isdeleted;
        this.dob = dob;
        this.mobile = mobile;
    }

}