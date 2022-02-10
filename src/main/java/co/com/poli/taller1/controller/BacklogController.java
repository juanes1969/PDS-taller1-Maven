package co.com.poli.taller1.controller;

import co.com.poli.taller1.entities.Backlog;
import co.com.poli.taller1.helper.ResponsesBuilder;
import co.com.poli.taller1.model.Response;
import co.com.poli.taller1.services.impl.BacklogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/backlog")
public class BacklogController {

    @Autowired
    private BacklogServiceImpl backlogService;

    @Autowired
    private ResponsesBuilder builder;

    @PostMapping
    private Response create(@Valid @RequestBody Backlog backlog, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(formatMessage(result));
        }
        backlogService.create(backlog);
        return builder.success(backlog);
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
