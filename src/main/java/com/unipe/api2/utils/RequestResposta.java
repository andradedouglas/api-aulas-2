package com.unipe.api2.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data @AllArgsConstructor
public class RequestResposta {
    private Object body;
    private HttpStatus status;

    public static ResponseEntity<Object> retornar(RequestResposta response) {
        return new ResponseEntity<>(response.getBody(), response.getStatus());
    }
}
