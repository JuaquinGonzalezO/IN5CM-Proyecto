/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.model;

import org.JoaquinGonzalez.system.Main;

/**
 *
 * @author Phant
 */
public class Categoria {
   private int  categoriaProductoId;
   private String nombreCategoria;
   private String descripcionCargo;

    public Categoria(int categoriaId, String NombreP, String DescripcionP) {
        this.categoriaProductoId = categoriaId;
        this.nombreCategoria = NombreP;
        this.descripcionCargo = DescripcionP;
    }

    public int getCategoriaId() {
        return categoriaProductoId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaProductoId = categoriaId;
    }

    public String getNombreP() {
        return nombreCategoria;
    }

    public void setNombreP(String NombreP) {
        this.nombreCategoria = NombreP;
    }

    public String getDescripcionP() {
        return descripcionCargo;
    }

    public void setDescripcionP(String DescripcionP) {
        this.descripcionCargo = DescripcionP;
    }

    @Override
    public String toString() {
        return "CategoriaProductos{" + "categoriaId=" + categoriaProductoId + ", NombreP=" + nombreCategoria + ", DescripcionP=" + descripcionCargo + '}';
    }
   
   
}
