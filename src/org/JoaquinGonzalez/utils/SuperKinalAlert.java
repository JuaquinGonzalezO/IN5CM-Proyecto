/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Phant
 */
public class SuperKinalAlert {
    private static SuperKinalAlert instance;
    
    private SuperKinalAlert(){
        
    }
    
    public static SuperKinalAlert getInstance(){
        if(instance == null ){
           instance = new SuperKinalAlert();   
       }
        return instance;
    }
    
    public void mostrarAlertaInformacion(int code){
       if (code == 400){
           Alert alert = new Alert (Alert.AlertType.INFORMATION);
       alert.setTitle("Confirmacion Rigistro");
       alert.setHeaderText("Confirmacion Registro");
       alert.setContentText("Tu Registro ha sido realizado con exito!");
       alert.showAndWait();
       }else if (code == 600 ){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Editar Tu Registro");
           alert.setHeaderText("Edicion Registro");
           alert.setContentText("!Tu Edicion de Registro se ha Realizado con exito!");
           alert.showAndWait();
       
       }else if (code == 700 ){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Faltan registros por completar");
           alert.setHeaderText("Faltan registros por completar");
           alert.setHeaderText("Registro vacio Porfavor Completa el Registro");
           alert.showAndWait();
        }
       
    }
    public Optional <ButtonType>mostrarAlertaConfirmacion(int code ){
        Optional<ButtonType> action = null;
      
         if(code == 800){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Elimina Registro");
            alert.setHeaderText("Eliminacion de Registro");
            alert.setContentText("¿Deseas Eliminar este Registro");
            action = alert.showAndWait();
         }else if (code == 950 ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Editar Registro");
            alert.setHeaderText("Editar Registro");
            alert.setContentText("¿Deseas Editar este Registro");
            action = alert.showAndWait();
         
       
    
         }
        return action;
    }
}