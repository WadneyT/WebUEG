package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

import com.br.exception.ResourceNotFoundException;
import com.br.model.Marca;
import com.br.repository.MarcaRepository;


@RequestMapping("/cmarca/")
@RestController
@CrossOrigin(origins="*")
public class MarcaController {
	
	
	//Cria o repositório JPA de forma automática e autogerenciado
	@Autowired
	private MarcaRepository mrep;
	
	
	//Método listar - trazer todas as marcas do banco
	@GetMapping("/marca")
	public List<Marca> listar(){
		
		return this.mrep.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
		
	}
	
	//Método consultar - trazer uma marca, caso exista, pelo codigo
	@GetMapping("/marca/{id}")
	public ResponseEntity<Marca> consultar(@PathVariable Long id) {
		
		Marca marca = this.mrep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Marca nao encontrada: " + id));
		
		return ResponseEntity.ok(marca);
		
	}
	
	
	//Método inserir - insere uma marca
	@PostMapping("/marca")
	public Marca inserir(@RequestBody Marca marca) {
		
		return this.mrep.save(marca);
		
	}
	
	

}
