package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LivrosRepository;
import com.example.demo.ExcecoesServices.LivrosNaoEncontrados;
import com.example.demo.doMain.Livro;



@Service
public class LivrosServices {

     @Autowired
    private LivrosRepository livrosRepository;

    public List <Livro> listar()
    {
        return livrosRepository.findAll();   
    }


    public Livro buscar (Long id)
    {
        Optional<Livro> livro = livrosRepository.findById(id);

        if(!livro.isPresent())
        {
            throw new LivrosNaoEncontrados("O livro nao pode ser encontr10ado");
        }     

        return livro.get();
    }


    public Livro salvar(Livro livro)
    {
        livro.setId(null);
        return livrosRepository.save(livro);   
    }


    public void deletar(Long id)
    {
        Optional<Livro> livro = livrosRepository.findById(id);

        if(!livro.isPresent())
        {
            throw new LivrosNaoEncontrados("O livro nao pode ser encontrado");
        }     

        livrosRepository.deleteById(id);
    }


    public void atualizar(Livro livro)
    {
        verificarExistencia(livro);
        livrosRepository.save(livro);     
    }


    private void verificarExistencia(Livro livro)
    {
        buscar(livro.getId());
    }
}
