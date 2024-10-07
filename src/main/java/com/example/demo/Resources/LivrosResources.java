package com.example.demo.Resources;

import java.net.URI;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.ExcecoesServices.LivrosNaoEncontrados;
import com.example.demo.Services.LivrosServices;
import com.example.demo.doMain.Livro;
import com.example.demo.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosServices livrosServices;

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> Listar()
    {
        return ResponseEntity.status(HttpStatus.OK).body(livrosServices.listar());
    }


    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<Void> Salvar(@RequestBody Livro livros)
    {
        livros = livrosServices.salvar(livros);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("{/id}").buildAndExpand(livros.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> Buscar(@PathVariable ("id") Long id)
    {
        Livro livro = livrosServices.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> Deletar (@PathVariable ("id") Long id)
    { 
        livrosServices.deletar(id);
        return ResponseEntity.noContent().build();    
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro,@PathVariable("id") Long id)
    {
        livro.setId(id);
        livrosServices.atualizar(livro);
        return ResponseEntity.noContent().build();
    }
}
