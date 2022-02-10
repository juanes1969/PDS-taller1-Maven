package co.com.poli.taller1.services;

import co.com.poli.taller1.entities.Task;

import java.util.List;

public interface TaskService {

    Task create(Task task);

    List<Task> findByProjectIdentifier(String projectIdetifier);

    double findByProjectIdentifierHours(String projectIdetifier);

    double findByProjectIdentifierHoursDeleted(String projectIdetifier, String status);

    Task deleteIdAndProjectIdentifier(Long idTask, String projectIdetifier);

}
