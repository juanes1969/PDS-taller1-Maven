package co.com.poli.taller1.services;

import co.com.poli.taller1.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project create(Project project);
}
