package com.api.parque_diversao.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.parque_diversao.services.exceptions.DatabaseException;
import com.api.parque_diversao.services.exceptions.ResourceNotFoundException;
import com.api.parque_diversao.utils.exceptions.NotaFiscalError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){

		String error = "Recurso não encontrado!";
		HttpStatus status = HttpStatus.NOT_FOUND; //Não encontrado 404
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){

		String error = "Erro de banco de dados!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

    @ExceptionHandler(NotaFiscalError.class)
    public ResponseEntity<StandardError> notaFiscal(NotaFiscalError e, HttpServletRequest request){

        String error = "Erro de NF!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(status).body(err);
    }

}
