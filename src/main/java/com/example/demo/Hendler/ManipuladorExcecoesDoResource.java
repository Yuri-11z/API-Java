package com.example.demo.Hendler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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




}
