package com.guilherme.mercantilapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Map;
import java.util.HashMap;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;
import org.springframework.dao.DataIntegrityViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarProdutoNaoEncontrado(
            ProdutoNaoEncontradoException exception) {

        return exception.getMessage();
    }

    @ExceptionHandler(VendaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarVendaNaoEncontrada(
            VendaNaoEncontradaException exception) {

        return exception.getMessage();
    }
    @ExceptionHandler(ClienteNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarClienteNaoEncontrado(
            ClienteNaoEncontradoException exception) {

        return exception.getMessage();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String tratarArgumentoInvalido(
            IllegalArgumentException exception) {

        return exception.getMessage();
    }
    @ExceptionHandler(EstoqueInsuficienteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String tratarEstoqueInsuficiente(
            EstoqueInsuficienteException exception) {

        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> tratarValidacao(
            MethodArgumentNotValidException exception) {

        Map<String, String> erros = new HashMap<>();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            String campo;
            if (error instanceof FieldError fieldError) {
                campo = fieldError.getField();
            } else {
                campo = error.getObjectName();
            }
            erros.put(campo, error.getDefaultMessage());
        }
        return erros;
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String tratarViolacaoIntegridade(
            DataIntegrityViolationException exception) {
        return "Não foi possível excluir este registro porque ele possui dados vinculados, como vendas.";
    }
}