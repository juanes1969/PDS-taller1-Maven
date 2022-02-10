package co.com.poli.taller1.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotEmpty(message = "El id no puede estar en blanco")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "El name no puede estar en blanco")
    private String name;

    @Column(name = "summary")
    @NotEmpty(message = "El summary no puede estar en blanco")
    private String summary;
    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;
    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    @Size(min = 1, max = 5)
    private int priority;


    @Column(name = "hours")
    @Size(min = 1, max = 8)
    @Positive(message = "Las horas deben ser positivas")
    private Double hours;
    @Column(name = "start_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;
    @Column(name = "end_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date endDate;
    @Column(name = "project_identifier", updatable = false)
    private String projectIdentifier;

    @JsonBackReference
    @JoinColumn(name = "backlog_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Backlog backlog;



}
