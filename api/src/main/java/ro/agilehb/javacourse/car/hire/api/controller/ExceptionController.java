package ro.agilehb.javacourse.car.hire.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import ro.agilehb.javacourse.car.hire.api.exceptions.BadRequestException;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.api.model.ErrorDTO;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = { BadRequestException.class, HttpClientErrorException.BadRequest.class })
    protected ResponseEntity<ErrorDTO> handleBadRequest(Exception exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setCode(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(value = NotFoundException.class )
    protected ResponseEntity<ErrorDTO> handleNotFound(Exception exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setCode(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(value = HttpServerErrorException.InternalServerError.class )
    protected ResponseEntity<ErrorDTO> handleInternalServerError(Exception exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getMessage());
        errorDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
