package com.example.EquipmentApi.contoller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController implements ErrorController {

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String handleError(HttpServletRequest request) {
        // Your logic to handle errors and return a custom error page or view
        return "errors";
    }

    @RequestMapping
    public ResponseEntity<String> handleErrorJson(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body("errors");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null) {
            return HttpStatus.valueOf(statusCode);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }


}
