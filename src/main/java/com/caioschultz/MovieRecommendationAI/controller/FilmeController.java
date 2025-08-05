package com.caioschultz.MovieRecommendationAI.controller;

import com.caioschultz.MovieRecommendationAI.model.FilmeDTO;
import com.caioschultz.MovieRecommendationAI.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criar(@RequestBody FilmeDTO filmeDTO){
        service.criar(filmeDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Filme cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<FilmeDTO> filmes = service.listar();
        if(!filmes.isEmpty()){
            return ResponseEntity.ok(filmes);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há filmes na lista!");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        FilmeDTO filme = service.listarPorId(id);
        if(filme != null){
            return ResponseEntity.ok(filme);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhum filme!");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody FilmeDTO filmeDTO){
        if(service.listarPorId(id) != null){
            service.atualizar(id, filmeDTO);
            return ResponseEntity.ok("Filme atualizado com sucesso!");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhum filme!");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        if(service.listarPorId(id) != null){
            service.deletar(id);
            return ResponseEntity.ok("Filme deletado com sucesso!");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " não corresponde a nenhum filme!");
        }
    }


}
