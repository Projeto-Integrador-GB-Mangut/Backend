package com.generation.mangut.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.mangut.model.Postagem;
import com.generation.mangut.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	public Optional<Postagem> curtir (Long id) {
		if (postagemRepository.existsById(id)) {
			Postagem postagem = postagemRepository.findById(id).get();
			
			postagem.setCurtir(postagem.getCurtir()+1);
			return Optional.of(postagemRepository.save(postagem));
		}
		
		return Optional.empty();
	}
	
	public Optional<Postagem> descurtir (Long id) {
		if (postagemRepository.existsById(id)) {
			Postagem postagem = postagemRepository.findById(id).get();
			
			postagem.setCurtir(postagem.getCurtir() - 1);
			return Optional.of(postagemRepository.save(postagem));
		}
		
		return Optional.empty();
	}
	
}