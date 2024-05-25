/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.model;

import java.sql.Blob;

/**
 *
 * @author Phant
 */
public class Producto {
    private int productoId;
    private String nombreProducto;
    private String descripcionProducto;
    private int cantidadStock;
    private double precionVentaUnitario;
    private double precioVentaMayor;
    private double precioCompra;
    private int distribuidorId;
    private int categoriaProductos;
    private Blob image;

    public Producto(int productoId, String nombreProducto, String descripcionProducto, int cantidadStock, double precionVentaUnitario, double precioVentaMayor, double precioCompra, int distribuidorId, int categoriaProductos, Blob image) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadStock = cantidadStock;
        this.precionVentaUnitario = precionVentaUnitario;
        this.precioVentaMayor = precioVentaMayor;
        this.precioCompra = precioCompra;
        this.distribuidorId = distribuidorId;
        this.categoriaProductos = categoriaProductos;
        this.image = image;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getPrecionVentaUnitario() {
        return precionVentaUnitario;
    }

    public void setPrecionVentaUnitario(double precionVentaUnitario) {
        this.precionVentaUnitario = precionVentaUnitario;
    }

    public double getPrecioVentaMayor() {
        return precioVentaMayor;
    }

    public void setPrecioVentaMayor(double precioVentaMayor) {
        this.precioVentaMayor = precioVentaMayor;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getDistribuidorId() {
        return distribuidorId;
    }

    public void setDistribuidorId(int distribuidorId) {
        this.distribuidorId = distribuidorId;
    }

    public int getCategoriaProductos() {
        return categoriaProductos;
    }

    public void setCategoriaProductos(int categoriaProductos) {
        this.categoriaProductos = categoriaProductos;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Producto{" + "productoId=" + productoId + ", nombreProducto=" + nombreProducto + ", descripcionProducto=" + descripcionProducto + ", cantidadStock=" + cantidadStock + ", precionVentaUnitario=" + precionVentaUnitario + ", precioVentaMayor=" + precioVentaMayor + ", precioCompra=" + precioCompra + ", distribuidorId=" + distribuidorId + ", categoriaProductos=" + categoriaProductos + ", imagen=" + image + '}';
    }

    
}
