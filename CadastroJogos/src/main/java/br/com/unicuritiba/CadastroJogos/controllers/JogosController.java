package br.com.unicuritiba.CadastroJogos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicuritiba.CadastroJogos.Repository.JogoRepository;
import br.com.unicuritiba.CadastroJogos.models.Jogo;

@RestController
public class JogosController {
	
	@Autowired
	JogoRepository repository;
	
	@GetMapping("/Jogos")
	public ResponseEntity<List<Jogo>> getJogos(){
		
		return ResponseEntity.ok(repository.findAll());		
	}
	
	@PostMapping("/Jogos")
	public ResponseEntity<Jogo> saveJogo(@RequestBody Jogo jogo){
		
		Jogo jogoSalvo = repository.save(jogo);
		return ResponseEntity.ok(jogoSalvo);	
	}
	
	@PutMapping("/Jogos/{id}")
	public ResponseEntity<Jogo> updateJogo(@PathVariable long id, @RequestBody Jogo jogoAtualizado) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		jogoAtualizado.setId(id);
		Jogo jogoSalvo = repository.save(jogoAtualizado);

		return ResponseEntity.ok(jogoSalvo);
	}
	@DeleteMapping("/Jogos/{id}")
	public void removeJogo(@PathVariable long id) {
		
		repository.deleteById(id);
	}

}
