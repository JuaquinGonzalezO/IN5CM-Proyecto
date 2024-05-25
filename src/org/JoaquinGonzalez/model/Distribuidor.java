/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.model;

/**
 *
 * @author Phant
 */
public class Distribuidor {
    private String nombreDistribuidor;
    private String direccionDistribuidor;
    private String nitDistribuidor;
    private String telefonoDistribuidor;
    private String web;

    public Distribuidor(String nombreDistribuidor, String direccionDistribuidor, String nitDistribuidor, String telefonoDistribuidor, String web) {
        this.nombreDistribuidor = nombreDistribuidor;
        this.direccionDistribuidor = direccionDistribuidor;
        this.nitDistribuidor = nitDistribuidor;
        this.telefonoDistribuidor = telefonoDistribuidor;
        this.web = web;
    }

    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public String getDireccionDistribuidor() {
        return direccionDistribuidor;
    }

    public void setDireccionDistribuidor(String direccionDistribuidor) {
        this.direccionDistribuidor = direccionDistribuidor;
    }

    public String getNitDistribuidor() {
        return nitDistribuidor;
    }

    public void setNitDistribuidor(String nitDistribuidor) {
        this.nitDistribuidor = nitDistribuidor;
    }

    public String getTelefonoDistribuidor() {
        return telefonoDistribuidor;
    }

    public void setTelefonoDistribuidor(String telefonoDistribuidor) {
        this.telefonoDistribuidor = telefonoDistribuidor;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Distribuidor{" + "nombreDistribuidor=" + nombreDistribuidor + ", direccionDistribuidor=" + direccionDistribuidor + ", nitDistribuidor=" + nitDistribuidor + ", telefonoDistribuidor=" + telefonoDistribuidor + ", web=" + web + '}';
    }
    
    
}
