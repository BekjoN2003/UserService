package isystem.uz.UserService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name =  ("applications"))
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ("user_id"), insertable = false, updatable = false)
    private User user;
    @Column(name =  ("user_id"))
    private Integer userId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ("job_id"),insertable = false,updatable = false)
    private Job job;
    @Column (name = ("job_id"))
    private Integer jobId;
    private Double salary;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean status;



}
