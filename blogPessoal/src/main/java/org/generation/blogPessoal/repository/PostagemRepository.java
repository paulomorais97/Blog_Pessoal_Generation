package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	// busca todos do titulo, mas não o titulo exato, sem levar em consideração maiusculo ou minusculo
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo); 
}
