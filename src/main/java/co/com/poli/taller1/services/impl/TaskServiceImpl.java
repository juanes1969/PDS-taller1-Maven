package co.com.poli.taller1.services.impl;

import co.com.poli.taller1.entities.Project;
import co.com.poli.taller1.entities.Task;
import co.com.poli.taller1.repository.ProjectRepository;
import co.com.poli.taller1.repository.TaskRepository;
import co.com.poli.taller1.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findByProjectIdentifier(String projectIdetifier) {
        List<Project> projects = projectRepository.findAll();
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectIdentifier().equals(projectIdetifier)) {
                return projects.get(i).getBacklog().getTasks();
            }
        }

        return null;
    }

    @Override
    public double findByProjectIdentifierHours(String projectIdetifier) {

        List<Task> taskList = findByProjectIdentifier(projectIdetifier);
        double hours = 0;

        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).getStatus().equals("deleted")) {
                hours += taskList.get(i).getHours();
            }
        }

        return hours;
    }

    @Override
    public double findByProjectIdentifierHoursDeleted(String projectIdetifier, String status) {
        List<Task> taskList = findByProjectIdentifier(projectIdetifier);

        double hours = 0;

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getStatus().equals(status)) {
                hours += taskList.get(i).getHours();
            }
        }

        return hours;
    }


    @Override
    public Task deleteIdAndProjectIdentifier(Long idTask, String projectIdetifier) {
        List<Task> taskList = findByProjectIdentifier(projectIdetifier);

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId() == idTask) {
                taskList.get(i).setStatus("deleted");
                taskRepository.delete(taskList.get(i));
                return taskList.get(i);
            }
        }
        return null;
    }


}
