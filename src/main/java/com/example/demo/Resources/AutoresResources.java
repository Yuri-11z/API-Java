package com.example.demo.Resources;

import java.net.URI;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.Services.AutoresServices;
import com.example.demo.doMain.Autor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Controller
@RequestMapping("/autores")
public class AutoresResources {

    @Autowired
     private AutoresServices autoresServices;

     @RequestMapping(method = RequestMethod.GET)
     public ResponseEntity<List<Autor>> listar()
     {
        List<Autor> autores = autoresServices.listar();
        return ResponseEntity.status(HttpStatus.OK).body(autores);
     }

     @RequestMapping(method = RequestMethod.POST)
     public ResponseEntity<Void> salvar(@RequestBody Autor autor)
     {
        autor = autoresServices.salvar(autor);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
     }

     
     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public ResponseEntity<Autor> buscar(@PathVariable ("id") Long id)
     {
         Autor autor = autoresServices.buscar(id);
         return ResponseEntity.status(HttpStatus.OK).body(autor);

     }

}
