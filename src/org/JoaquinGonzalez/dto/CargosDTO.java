/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.dto;

import org.JoaquinGonzalez.model.Cargos;

/**
 *
 * @author Phant
 */
public class CargosDTO {
private static CargosDTO instance;
    private Cargos cargos;
    private CargosDTO(){
        
    }
    
    public static CargosDTO getCargosDTO(){
        if(instance == null ){
            instance = new CargosDTO();
                
        }
        return instance;
    }

    public Cargos getCargos() {
        return cargos;
    }

    public void setCargos(Cargos cliente) {
        this.cargos = cliente;
    }
    
}
