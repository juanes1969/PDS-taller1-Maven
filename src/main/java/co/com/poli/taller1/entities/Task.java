package co.com.poli.taller1.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "summary")
    private String summary;
    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;
    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private int priority;


    @Column(name = "hours")
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
