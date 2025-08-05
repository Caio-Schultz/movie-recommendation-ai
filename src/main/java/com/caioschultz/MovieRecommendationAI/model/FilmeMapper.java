package com.caioschultz.MovieRecommendationAI.model;

import org.springframework.stereotype.Component;

@Component
public class FilmeMapper {

    public FilmeModel map(FilmeDTO filmeDTO){
        FilmeModel filmeModel = new FilmeModel();
        filmeModel.setId(filmeDTO.getId());
        filmeModel.setNome(filmeDTO.getNome());
        filmeModel.setFaixaEtaria(filmeDTO.getFaixaEtaria());
        filmeModel.setGenero(filmeDTO.getGenero());
        filmeModel.setDescricao(filmeDTO.getDescricao());

        return filmeModel;
    }

    public FilmeDTO map(FilmeModel filmeModel){
        FilmeDTO filmeDTO = new FilmeDTO();
        filmeDTO.setId(filmeModel.getId());
        filmeDTO.setNome(filmeModel.getNome());
        filmeDTO.setFaixaEtaria(filmeModel.getFaixaEtaria());
        filmeDTO.setGenero(filmeModel.getGenero());
        filmeDTO.setDescricao(filmeModel.getDescricao());

        return filmeDTO;
    }

}
