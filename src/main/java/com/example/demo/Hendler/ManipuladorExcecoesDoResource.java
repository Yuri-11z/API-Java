package com.example.demo.Hendler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.ExcecoesServices.AutoresExistentes;
import com.example.demo.ExcecoesServices.AutoresNaoEncontrados;
import com.example.demo.ExcecoesServices.LivrosNaoEncontrados;
import com.example.demo.doMain.DetalhesErro;

@ControllerAdvice
public class ManipuladorExcecoesDoResource {

    @ExceptionHandler(LivrosNaoEncontrados.class)
    public ResponseEntity<DetalhesErro> HandlelivroNaoEncontradoException(LivrosNaoEncontrados e, HttpServletRequest request)
    {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo(e.getMessage());
        erro.setMensagemDesenvolvedor("Erro de digitação.");
        erro.setTimeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(AutoresExistentes.class)
    public ResponseEntity<DetalhesErro> HandlerAutoresExistentes(AutoresExistentes e, HttpServletRequest request)
    {
        DetalhesErro erros = new DetalhesErro();

        erros.setStatus(409L);
        erros.setTitulo(e.getMessage());
        erros.setMensagemDesenvolvedor("Erro de digitação");
        erros.setTimeStamp(System.currentTimeMillis());


        return ResponseEntity.status(HttpStatus.CONFLICT).body(erros);
    }
    @ExceptionHandler(AutoresNaoEncontrados.class)
    public ResponseEntity<DetalhesErro> HandlerAutoresNaoEncontrados(AutoresNaoEncontrados e, HttpServletRequest request)
    {
        DetalhesErro erros = new DetalhesErro();

        erros.setStatus(404L);
        erros.setTitulo(e.getMessage());
        erros.setMensagemDesenvolvedor("O autor não pôde ser encontrado");
        erros.setTimeStamp(System.currentTimeMillis());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> HandlerDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request)
    {
        DetalhesErro erros = new DetalhesErro();

        erros.setStatus(404L);
        erros.setTitulo("Requisição Invalida");
        erros.setMensagemDesenvolvedor("O autor não pôde ser encontrado");
        erros.setTimeStamp(System.currentTimeMillis());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

}
