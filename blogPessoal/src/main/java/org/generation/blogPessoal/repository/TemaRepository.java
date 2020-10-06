package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {

	// busca todas as descrições sem levar em consideração maiusculo ou minusculo

	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}
