package com.generation.mangut.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.mangut.model.Tema;
import com.generation.mangut.repository.TemaRepository;

@RestController
@RequestMapping ("/temas")
@CrossOrigin(origins = "*", allowedHeaders= "*")
public class TemaController {
	
	@Autowired 
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id){
		return temaRepository.findById(id)
				.map (resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
   @GetMapping("/descricao/{descricao}")
   public ResponseEntity<List<Tema>> getByDescricao (@PathVariable String descricao){
	   return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
   }
   
   @GetMapping ("/categoria/{categoria}")
   public ResponseEntity<List<Tema>> getByCategoria (@PathVariable String categoria){
	   return ResponseEntity.ok(temaRepository.findAllByCategoriaContainingIgnoreCase(categoria));
   }
   
   @PostMapping
   
   public ResponseEntity<Tema> post (@RequestBody @Valid Tema tema){
	   return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
   }
  
   @PutMapping
   public ResponseEntity<Tema> put (@RequestBody @Valid Tema tema){
	   if(!temaRepository.existsById(tema.getId()) || tema.getId()==null){
		   return ResponseEntity.badRequest().build();
	   }
	   return ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema));
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<Tema> delete (@PathVariable Long id){
	   if(!temaRepository.existsById(id) || id == null) {
		   return ResponseEntity.notFound().build();
	   }
	   
	   temaRepository.deleteById(id);
	   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
   
  
}

