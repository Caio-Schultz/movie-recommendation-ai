package com.caioschultz.MovieRecommendationAI.enums;

public enum FaixaEtaria {

    LIVRE("L", 0),
    MAIS_10("+10", 10),
    MAIS_12("+12", 12),
    MAIS_14("+14", 14),
    MAIS_16("+16", 16),
    MAIS_18("+18", 18);

    private String rotulo;
    private int idadeMinima;

    FaixaEtaria(String rotulo, int idadeMinima) {
        this.rotulo = rotulo;
        this.idadeMinima = idadeMinima;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public int getIdadeMinima() {
        return idadeMinima;
    }

    public void setIdadeMinima(int idadeMinima) {
        this.idadeMinima = idadeMinima;
    }
}
