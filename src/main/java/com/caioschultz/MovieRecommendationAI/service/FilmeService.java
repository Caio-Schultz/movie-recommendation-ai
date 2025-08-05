package com.caioschultz.MovieRecommendationAI.service;

import com.caioschultz.MovieRecommendationAI.model.FilmeDTO;
import com.caioschultz.MovieRecommendationAI.model.FilmeMapper;
import com.caioschultz.MovieRecommendationAI.model.FilmeModel;
import com.caioschultz.MovieRecommendationAI.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    private FilmeRepository repository;
    private FilmeMapper filmeMapper;

    public FilmeService(FilmeRepository repository, FilmeMapper filmeMapper) {
        this.repository = repository;
        this.filmeMapper = filmeMapper;
    }

    public FilmeDTO criar(FilmeDTO filmeDTO){
        FilmeModel filme = repository.save(filmeMapper.map(filmeDTO));
        return filmeMapper.map(filme);
    }

    public List<FilmeDTO> listar(){
        List<FilmeModel> filmes = repository.findAll();
        return filmes.stream()
                .map(filmeMapper::map)
                .collect(Collectors.toList());
    }

    public FilmeDTO listarPorId(Long id){
        Optional<FilmeModel> filme = repository.findById(id);
        return filme.map(filmeMapper::map).orElse(null);
    }

    public FilmeDTO atualizar(Long id, FilmeDTO filmeDTO){
        Optional<FilmeModel> filme = repository.findById(id);
        if(filme.isPresent()){
            FilmeModel filmeAtualizado = filmeMapper.map(filmeDTO);
            filmeAtualizado.setId(id);
            FilmeModel filmeSalvo = repository.save(filmeAtualizado);
            return filmeMapper.map(filmeSalvo);
        }
        else {
            return null;
        }
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

}
