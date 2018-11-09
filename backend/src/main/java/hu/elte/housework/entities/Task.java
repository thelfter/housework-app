package hu.elte.housework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Task implements Serializable {
    @Id
    @Column(name = "task_id")
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

    @Column(name = "CREATED_DATE", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate createdDate;

    @Column()
    private LocalDate dueDate;


    @Column(name = "IS_COMPLETED", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable
    private List<TaskCategory> categories;

    @PrePersist
    protected void prePersist() {
        if(isCompleted == null) { isCompleted = false; }
        if(createdDate == null) { createdDate = LocalDate.now(); }
    }

}