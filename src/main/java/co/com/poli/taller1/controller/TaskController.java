package co.com.poli.taller1.controller;

import co.com.poli.taller1.entities.Task;
import co.com.poli.taller1.helper.ResponsesBuilder;
import co.com.poli.taller1.model.Response;
import co.com.poli.taller1.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private ResponsesBuilder builder;


    @PostMapping
    private Response create(@Valid @RequestBody Task task, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(formatMessage(result));
        }
        taskService.create(task);
        return builder.success(task);
    }


    @GetMapping("/project/{projectIdentifier}")
    private Response findByProjectIdentifier(@PathVariable("projectIdentifier") String projectIdentifier) {

        List<Task> tasks = taskService.findByProjectIdentifier(projectIdentifier);

        if (tasks.isEmpty()) {
            return builder.failedNotFound("List task is empy");
        }

        return builder.successFind(tasks);
    }

    @GetMapping("/hours/project/{projectIdentifier}")
    private Response findByProjectIdentifierHours(@PathVariable("projectIdentifier") String projectIdentifier) {

        double tasks = taskService.findByProjectIdentifierHours(projectIdentifier);

        if (tasks == 0) {
            return builder.failedNotFound("The project has not started yet or has no active hours");
        }

        return builder.successFind(tasks);
    }

    @GetMapping("/hours/project/{projectIdentifier}/{status}")
    private Response findByProjectIdentifierHoursDeleted(@PathVariable("projectIdentifier") String projectIdentifier, @PathVariable("status") String status) {

        double tasks = taskService.findByProjectIdentifierHoursDeleted(projectIdentifier, status);

        if (tasks == 0) {
            return builder.failedNotFound("The project has not started yet or has no active hours");
        }

        return builder.successFind(tasks);
    }

    @DeleteMapping("/{idTask}/{projectIdentifier}")
    public Response deleteTask(@PathVariable("idTask") Long idTask, @PathVariable("projectIdentifier") String projectIdentifier) {

        return builder.successFind(taskService.deleteIdAndProjectIdentifier(idTask, projectIdentifier));
    }


    private List<Map<String, String>> formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        return errors;
    }
}
