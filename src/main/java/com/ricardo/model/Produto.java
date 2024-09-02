package com.ricardo.model;

public class Produto {
    private int idProduto;
    private String descricaoProduto;
    private String situacao;
    private float valorUnitario;
    private Marca marca;
    private UnidadeDeMedida unidadeDeMedida;

    public Produto() {
    }

    public Produto(int idProduto, String descricaoProduto, String situacao, float valorUnitario, Marca marca, UnidadeDeMedida unidadeDeMedida) {
        this.idProduto = idProduto;
        this.descricaoProduto = descricaoProduto.toUpperCase();
        this.situacao = situacao.toUpperCase();
        this.valorUnitario = valorUnitario;
        this.marca = marca;
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto.toUpperCase();
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao.toUpperCase();
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }
    
    
}
