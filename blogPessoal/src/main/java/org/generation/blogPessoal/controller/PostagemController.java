package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/postagens")
@Api(value="API Blog Pessoal")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	@ApiOperation(value="Lista todos as postagens")
	public ResponseEntity<List<Postagem>> findAllPostagem(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Lista as postagens por ID")
	public ResponseEntity<Postagem> findByIdPostagem(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		
		// Pode retorna tanto um objeto do tipo postagem
		// como um notFound caso tenha um erro na requisição
	}
	
	@GetMapping("/titulo/{titulo}")
	@ApiOperation(value="Lista as postagens por titulo")
	public ResponseEntity<List<Postagem>>FindByTituloPostagem(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	@ApiOperation(value="Insere uma postagem")
	public ResponseEntity<Postagem> postPostagem (@RequestBody Postagem postagem){
	 return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	 
	// SALVA OS DADOS DA POSTAGEM
	}
	
	@PutMapping
	@ApiOperation(value="Atualiza / Altera uma postagem")
	public ResponseEntity<Postagem> putPostagem (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Exclui uma postagem")
	public void  deletePostagem(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
	
}
