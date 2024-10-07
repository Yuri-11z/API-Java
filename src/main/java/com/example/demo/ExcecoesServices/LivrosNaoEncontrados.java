package com.example.demo.ExcecoesServices;

public class LivrosNaoEncontrados extends RuntimeException{


    public LivrosNaoEncontrados (String mensagem){
        super(mensagem);
    }

    public LivrosNaoEncontrados (String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
