package co.com.poli.taller1.controller;

import co.com.poli.taller1.entities.Project;
import co.com.poli.taller1.helper.ResponsesBuilder;
import co.com.poli.taller1.model.Response;
import co.com.poli.taller1.services.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private ResponsesBuilder builder;

    @GetMapping
    private Response findAll() {
        List<Project> projects = projectService.findAll();
        if (projects.isEmpty()){
            return builder.failedNotFound("Projects is empty");
        }
        return builder.successFind(projects);
    }

    @PostMapping
    private Response create(@Valid @RequestBody Project project, BindingResult result) {
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        projectService.create(project);
        return builder.success(project);
    }

    private  List<Map<String,String>> formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        return errors;
    }
}
