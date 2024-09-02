package com.ricardo.model;

public class UnidadeDeMedida {
    private int idUnidadeDeMedida;
    private String descricaoUnidadeDeMedida;

    public UnidadeDeMedida() {
    }

    public UnidadeDeMedida(int idUnidadeDeMedida, String descricaoUnidadeDeMedida) {
        this.idUnidadeDeMedida = idUnidadeDeMedida;
        this.descricaoUnidadeDeMedida = descricaoUnidadeDeMedida.toUpperCase();
    }

    public UnidadeDeMedida(int idUnidadeDeMedida) {
        this.idUnidadeDeMedida = idUnidadeDeMedida;
    }

    public int getIdUnidadeDeMedida() {
        return idUnidadeDeMedida;
    }

    public void setIdUnidadeDeMedida(int idUnidadeDeMedida) {
        this.idUnidadeDeMedida = idUnidadeDeMedida;
    }

    public String getDescricaoUnidadeDeMedida() {
        return descricaoUnidadeDeMedida;
    }

    public void setDescricaoUnidadeDeMedida(String descricaoUnidadeDeMedida) {
        this.descricaoUnidadeDeMedida = descricaoUnidadeDeMedida.toUpperCase();
    }
}
