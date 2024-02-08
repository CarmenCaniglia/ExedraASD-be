package carmencaniglia.exedraAsd.exceptions;

import carmencaniglia.exedraAsd.payloads.ErrorsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class) //400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public ErrorsDTO hanleBusinessException(BusinessException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) //401
    public ErrorsDTO handleUnauthorized(UnauthorizedException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403 --> non ci sono i permessi di accesso
    public ErrorsDTO handleAccessDenied(AccessDeniedException ex) {
        return new ErrorsDTO("Il tuo ruolo non permette di accedere a questa funzionalità!", LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class) //404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());

    }
    @ExceptionHandler(Exception.class) //500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGenericError(Exception ex){
        ex.printStackTrace();
        return new ErrorsDTO("Problema lato server!!!", LocalDateTime.now());
    }

}
