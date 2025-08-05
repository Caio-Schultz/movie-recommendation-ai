package com.caioschultz.MovieRecommendationAI.repository;

import com.caioschultz.MovieRecommendationAI.model.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeModel, Long> {
}
