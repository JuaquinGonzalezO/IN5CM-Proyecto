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
public class TicketSoporte {
    private int ticketSoporteId;
    private String descripcion;
    private String estatus;
    private int clienteId;
    private String cliente;
    private int facturaId;

    public TicketSoporte(int ticketSoporteId, String descripcion, String estatus, String cliente, int facturaId) {
        this.ticketSoporteId = ticketSoporteId;
        this.descripcion = descripcion;
        this.estatus = estatus;
        this.cliente = cliente;
        this.facturaId = facturaId;
    }

    public int getTicketSoporteId() {
        return ticketSoporteId;
    }

    public void setTicketSoporteId(int ticketSoporteId) {
        this.ticketSoporteId = ticketSoporteId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    @Override
    public String toString() {
        return "TicketSoporte{" + "ticketSoporteId=" + ticketSoporteId + ", descripcion=" + descripcion + ", estatus=" + estatus + ", clienteId=" + clienteId + ", cliente=" + cliente + ", facturaId=" + facturaId + '}';
    }
   
  
}
