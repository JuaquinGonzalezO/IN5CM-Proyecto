/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.JoaquinGonzalez.system.Main;
/**
 *
 * @author Phant
 */
public class MenuPrincipalController implements Initializable {
    private Main stage;
    @FXML
    MenuItem btnClientes,btnTicketSoporte,btnMenuProducto,btnCargos,btnCompras,btnCategorias,btnDistribuidores,btnEmpleados,btnFacturas,btnDetalle,btnPromociones;
    
    @FXML
    public void handleButtonAction(ActionEvent event)throws Exception{
        if(event.getSource()== btnClientes){
            stage.menuClienteView();
        } else if(event.getSource() == btnTicketSoporte){
            stage.menuTicketSoporteView();
        } else if(event.getSource() == btnMenuProducto){
            stage.menuProductoView();    
        } else if(event.getSource() == btnCargos){
            stage.menuCargoView();  
        } else if(event.getSource() == btnCompras){
            stage.menuCompraView();  
        } else if(event.getSource() == btnCategorias){
            stage.menuCategoriaProductoView();  
        } else if(event.getSource() == btnDistribuidores){
            stage.menuDistribuidorView();        
        } else if(event.getSource() == btnEmpleados){
            stage.menuEmpleadosView();  
        } else if(event.getSource() == btnFacturas){
            stage.menuFacturaView();   
        } else if(event.getSource() == btnDetalle){
            stage.menuDetalleFacturaView();  
        } else if(event.getSource() == btnPromociones){
            stage.menuPromocionView();     
    }
        }
        
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

   
    
}
