package co.com.poli.taller1.helper;

import co.com.poli.taller1.model.Response;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.*;

@Component
public class ResponsesBuilder {

    public Response success(Object data) {
        return Response.builder()
                .code(CREATED.value())
                .data(data)
                .build();
    }

    public Response successFind(Object data) {
        return Response.builder()
                .code(OK.value())
                .data(data)
                .build();
    }

    public Response failed(Object data){
        return Response.builder()
                .code(BAD_REQUEST.value())
                .data(data)
                .build();
    }

    public Response failedNotFound(Object data){
        return Response.builder()
                .code(NOT_FOUND.value())
                .data(data)
                .build();
    }


}
