package hu.elte.housework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String taskName;

    @Column
    @NotNull
    private String taskDescription;

    @Column
    @NotNull
    private Integer score;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Column
    @NotNull
    private Boolean completed;

}
