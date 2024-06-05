package org.JoaquinGonzalez.model;

public class Encargados {
    private int encargadoId;
    
    public Encargados() {
    }

    public Encargados(int encargadoId) {
        this.encargadoId = encargadoId;
    }

    public int getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(int encargadoId) {
        this.encargadoId = encargadoId;
    }

    @Override
    public String toString() {
        return "Id: " + encargadoId;
    }

    
}
