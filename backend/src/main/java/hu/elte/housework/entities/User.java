package hu.elte.housework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.SchemaOutputResolver;
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
    private String fullName;

    @Column
    private LocalDate lastLogin;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @Column(name = "actualScore", columnDefinition = "Decimal(10) default '0'")
    private Integer actualScore;

    @Column(name = "sumScore", columnDefinition = "Decimal(10) default '0'")
    private Integer sumScore;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private Room room;

    public enum Role {
        ROLE_GUEST, ROLE_USER, ROLE_OWNER, ROLE_ADMIN
    }

    public void addScore(Integer scorePoint) {
        this.actualScore += scorePoint;
        this.sumScore += actualScore;
    }

    @PrePersist
    protected void onCreate() {
        if (actualScore == null) { actualScore = 0; }
        if (sumScore == null) { sumScore = 0; }
        if(tasks == null) { tasks = new ArrayList<>(); }
    }

}
