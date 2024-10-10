package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ExcecoesServices.AutoresExistentes;
import com.example.demo.doMain.Autor;
import com.example.demo.repository.AutoresRepository;

@Service
public class AutoresServices {

    @Autowired
    private AutoresRepository autoresRepository;

    public List<Autor> listar(){
        return autoresRepository.findAll();
    }
    
    public Autor salvar(Autor autor)
    {
        if (autor.getId() != null)
        {
            Optional<Autor> a = autoresRepository.findById(autor.getId());
            
            if (a != null ){
                throw new AutoresExistentes("O autor já existe!");
            }
        }

        if(autor.getNome() == null || StringUtils.isBlank(autor.getNome()))
        {
            throw new AutoresExistentes("O nome não pode ser nulo");
        }

        return autoresRepository.save(autor);   
    }

    public Autor buscar(Long id)
    {
        Optional<Autor> autor = autoresRepository.findById(id);

        if(!autor.isPresent())
        {
            throw new AutoresExistentes("Autor não encontrado");
        }
        return autor.get();
    }

}
