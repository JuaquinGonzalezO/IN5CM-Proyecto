/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.dto;

import org.JoaquinGonzalez.model.Facturas;
 
public class FacturaDTO {
    private static FacturaDTO instance;
    private Facturas factura;
    private FacturaDTO(){
    }
    public static FacturaDTO getFacturaDTO(){
        if(instance == null){
            instance = new FacturaDTO();
        }
        return instance;
    }
 
    public Facturas getFactura() {
        return factura;
    }
 
    public void setFactura(Facturas factura) {
        this.factura = factura;
    }
}