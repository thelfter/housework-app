package hu.elte.housework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Task {
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

    @PrePersist
    public void prePersist() {
        this.isCompleted = false;
        this.createdDate = LocalDate.now();
    }

}
