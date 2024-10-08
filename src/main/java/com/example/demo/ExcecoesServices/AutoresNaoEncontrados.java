package com.example.demo.ExcecoesServices;

public class AutoresNaoEncontrados extends RuntimeException {

    public AutoresNaoEncontrados(String mensagem)
    {
        super(mensagem);
    }

    public AutoresNaoEncontrados(String mensagem, Throwable causa)
    {
        super(mensagem, causa);
    }

}
