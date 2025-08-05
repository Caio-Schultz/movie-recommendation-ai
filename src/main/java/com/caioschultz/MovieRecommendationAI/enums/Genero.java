package com.caioschultz.MovieRecommendationAI.enums;

public enum Genero {

    ACAO("Ação"),
    AVENTURA("Aventura"),
    ANIMACAO("Animação"),
    COMEDIA("Comédia"),
    DRAMA("Drama"),
    FICCAO_CIENTIFICA("Ficção científica"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense"),
    TERROR("Terror");

    private String nome;

    Genero(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
