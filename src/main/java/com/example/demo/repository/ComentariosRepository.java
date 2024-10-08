package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.doMain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long>  {



}
