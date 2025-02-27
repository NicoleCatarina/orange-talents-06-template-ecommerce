package br.com.zupacademy.nicolecatarina.mercadolivre.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorBody handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<org.springframework.validation.FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return buildErrorResponse(fieldErrors);
    }

    private ErrorBody buildErrorResponse(List<org.springframework.validation.FieldError> erros) {
        List<FieldError> errorsBody = erros.stream()
                .map(erro -> new FieldError(erro.getField(), messageSource.getMessage(erro, LocaleContextHolder.getLocale())))
                .collect(Collectors.toList());

        return new ErrorBody(errorsBody);
    }

}
