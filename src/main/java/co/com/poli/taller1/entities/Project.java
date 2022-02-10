package co.com.poli.taller1.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "projects")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "project_name", unique = true)
    @NotEmpty(message = "El projectName no puede estar en blanco")
    private String projectName;
    @Column(name = "project_identifier", unique = true, updatable = false)
    @NotEmpty(message = "El projectIdentifier no puede estar en blanco")
    @Size(min = 5, max = 7)
    private String projectIdentifier;
    @Column(name = "description")
    @NotEmpty(message = "La description no puede estar en blanco")
    private String description;
    @Column(name = "start_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;
    @Column(name = "end_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date endDate;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "project")
    @JoinColumn(name = "backlog_id")
    private Backlog backlog;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(projectName, project.projectName) && Objects.equals(projectIdentifier, project.projectIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
