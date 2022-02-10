package co.com.poli.taller1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "backlogs")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "project_identifier")
    @NotEmpty(message = "El projectIdentifier no puede estar en blanco")
    private String projectIdentifier;
    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;
    @JsonManagedReference
    @OneToMany(mappedBy = "backlog", cascade = CascadeType.PERSIST)
    private List<Task> tasks;
}


