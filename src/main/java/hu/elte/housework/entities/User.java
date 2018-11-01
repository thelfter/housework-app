package hu.elte.housework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @Column
    private LocalDate lastLogin;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @Column(name = "score", columnDefinition = "Decimal(10) default '0'")
    private Integer score;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private Room room;

    public enum Role {
        ROLE_GUEST, ROLE_USER, ROLE_OWNER, ROLE_ADMIN
    }

    public void setScore(Integer scorePoint) {
        this.score += scorePoint;
    }

    @PrePersist
    protected void onCreate() {
        if (score == null) { score = 0; }
        if(tasks == null) { tasks = new ArrayList<>(); }
    }

}
