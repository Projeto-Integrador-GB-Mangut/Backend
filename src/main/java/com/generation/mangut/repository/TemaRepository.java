package com.generation.mangut.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.mangut.model.Tema;



@Repository
public interface TemaRepository extends JpaRepository <Tema, Long> {
	
	public List <Tema> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	public List <Tema> findAllByCategoriaContainingIgnoreCase (@Param("categoria") String categoria);

}
 
