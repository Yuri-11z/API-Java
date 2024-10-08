package com.example.demo.ExcecoesServices;

public class AutoresExistentes extends RuntimeException{

    public AutoresExistentes(String mensagem){
        super(mensagem);
    }

    public AutoresExistentes(String mensagem, Throwable causa)
    {
        super(mensagem, causa);
    }
}
