package com.generation.mangut.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagens")
public class Postagem {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@UpdateTimestamp
	private LocalDateTime date;
	
	@NotBlank (message = "O título é obrigatório!")
	@Size (min = 10, max = 200, message = "O título deve conter de 10 a 200 caracteres.")
	private String titulo;
	
	@NotBlank (message = "O texto é obrigatório!")
	@Size (min = 10, max = 1000, message = "O texto deve conter de 10 a 1000 caracteres.")
	private String texto;
	
	private String midia;
	
	@Column (columnDefinition = "integer default 0")
	private int curtir;
	
	@Size (max = 100, message = "A palavra-chave deve conter no máximo 100 caracteres.")
	private String palavraChave;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getMidia() {
		return midia;
	}

	public void setMidia(String midia) {
		this.midia = midia;
	}

	public int getCurtir() {
		return curtir;
	}

	public void setCurtir(int curtir) {
		this.curtir = curtir;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	
}
