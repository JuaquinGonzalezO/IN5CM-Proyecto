/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.dto;

/**
 *
 * @author informatica
 */
public class ClienteDTO {
    private static ClienteDTO instance;
    
    private ClienteDTO(){
        
    }
    
    public static ClienteDTO getInstance(){
        if(instance == null ){
            instance = new ClienteDTO();
                
        }
        return instance;
    }
}
