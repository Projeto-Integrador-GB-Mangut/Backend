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

import com.generation.mangut.model.Postagem;
import com.generation.mangut.repository.PostagemRepository;
import com.generation.mangut.service.PostagemService;

@RestController
@RequestMapping ("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private PostagemService postagemService;
	
	@GetMapping
	public ResponseEntity <List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@GetMapping("/texto/{texto}")
	public ResponseEntity <List<Postagem>> getByTexto(@PathVariable String texto){
		return ResponseEntity.ok(postagemRepository.findAllByTextoContainingIgnoreCase(texto));
	}
	
	@GetMapping("/palavrachave/{palavraChave}")
	public ResponseEntity <List<Postagem>> getByPalavraChave(@PathVariable String palavraChave){
		return ResponseEntity.ok(postagemRepository.findAllByPalavraChaveContainingIgnoreCase(palavraChave));
	}
	
	@PutMapping("curtir/{id}")
	public ResponseEntity <Postagem> curtir (@PathVariable Long id) {
		return postagemService.curtir(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.badRequest().build());
	}
	
	@PutMapping("descurtir/{id}")
	public ResponseEntity <Postagem> descurtir (@PathVariable Long id) {
		return postagemService.descurtir(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.badRequest().build());
	}
	
	@PostMapping
	public ResponseEntity <Postagem> post(@RequestBody @Valid Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity <Postagem> put(@RequestBody @Valid Postagem postagem){
		if(!postagemRepository.existsById(postagem.getId()) || postagem.getId() == null){
			return ResponseEntity.notFound().build();
		}
		postagemRepository.save(postagem);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <Postagem> deleteById(@PathVariable Long id){
		if(!postagemRepository.existsById(id) || id == null){
			return ResponseEntity.notFound().build();
		}
		postagemRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


}
