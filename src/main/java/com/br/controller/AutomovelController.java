package com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Automovel;
import com.br.repository.AutomovelRepository;

@RestController
@RequestMapping("/cautomovel")
@CrossOrigin(origins = "*")
public class AutomovelController {

    @Autowired
    private AutomovelRepository arep;

    // LISTAR TODOS
    @GetMapping("/automovel")
    public List<Automovel> listar() {
        return this.arep.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
    }

    // BUSCAR POR ID
    @GetMapping("/automovel/{id}")
    public ResponseEntity<Automovel> consultar(@PathVariable Long id) {

        Automovel automovel = this.arep.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Automovel nao encontrado: " + id));

        return ResponseEntity.ok(automovel);
    }

    // INSERIR
    @PostMapping("/automovel")
    public Automovel inserir(@RequestBody Automovel automovel) {
        return this.arep.save(automovel);
    }

    // ATUALIZAR
    @PutMapping("/automovel/{id}")
    public ResponseEntity<Automovel> atualizar(@PathVariable Long id,
                                               @RequestBody Automovel dados) {

        Automovel automovel = this.arep.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Automovel nao encontrado: " + id));

        automovel.setNome(dados.getNome());
        automovel.setModelo(dados.getModelo());
        automovel.setDataFabricacao(dados.getDataFabricacao());
        automovel.setQuantidade(dados.getQuantidade());
        automovel.setPrecoVenda(dados.getPrecoVenda());
        automovel.setTrioEletrico(dados.isTrioEletrico());
        automovel.setMarca(dados.getMarca());

        Automovel atualizado = this.arep.save(automovel);

        return ResponseEntity.ok(atualizado);
    }

    // DELETAR
    @DeleteMapping("/automovel/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {

        Automovel automovel = this.arep.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Automovel nao encontrado: " + id));

        this.arep.delete(automovel);

        return ResponseEntity.ok().build();
    }
}
