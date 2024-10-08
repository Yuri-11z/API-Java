package com.example.demo.Services;


import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ComentariosRepository;
import com.example.demo.repository.LivrosRepository;
import com.example.demo.ExcecoesServices.LivrosNaoEncontrados;
import com.example.demo.doMain.Comentario;
import com.example.demo.doMain.Livro;



@Service
public class LivrosServices {

     @Autowired
    private LivrosRepository livrosRepository;
    
    @Autowired
    private ComentariosRepository comentariosRepository;

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

    public Comentario salvarComentario(Long livroId, Comentario comentario)
    {
        Livro livro = buscar(livroId);
        comentario.setLivro(livro);
        comentario.setData(new Date());

        return comentariosRepository.save(comentario);

    }

    public List<Comentario> listaComentarios(Long livroId){
        Livro livro = buscar(livroId);

        return livro.getComentarios();
    }
}
