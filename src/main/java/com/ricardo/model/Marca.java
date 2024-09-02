package com.ricardo.model;

public class Marca {
    private int idMarca;
    private String descricaoMarca;
    
    public Marca() {
        
    }
    
    public Marca(int idMarca, String descricaoMarca) {
        this.idMarca = idMarca;
        this.descricaoMarca = descricaoMarca.toUpperCase();
    }

    public Marca(int idMarca) {
        this.idMarca = idMarca;
    }
    

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescricaoMarca() {
        return descricaoMarca;
    }

    public void setDescricaoMarca(String descricaoMarca) {
        this.descricaoMarca = descricaoMarca.toUpperCase();
    }
}
