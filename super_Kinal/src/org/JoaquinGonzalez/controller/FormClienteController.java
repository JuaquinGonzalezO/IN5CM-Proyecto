/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.controller;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.JoaquinGonzalez.dao.Conexion;
import org.JoaquinGonzalez.system.Main;

/**
 *
 * @author Phant
 */
public class FormClienteController implements Initializable {
private Main stage;
 
private static Connection conexion = null;
private static PreparedStatement statement = null;


@FXML
TextField tfClienteId, tfNombre, tfApellido,tfTelefono,tfDireccion,tfNit;
@FXML
Button btnGuardar,btnCancelar;


@FXML
public void handleButtonAction(ActionEvent event) throws SQLException {
    if (event.getSource() == btnCancelar){
        stage.menuClienteView();
    }else if (event.getSource() == btnGuardar){
        agregarCliente();
         stage.menuClienteView();
    }
    
}



@Override
  public void initialize(URL location, ResourceBundle resources) {
      //TODO
  }

  public void agregarCliente() throws SQLException{
      try{
          conexion = Conexion.getInstance().obtenerConexion();
          String sql = "CALL sp_AgregarCliente(?, ?, ?, ?, ?)";
          statement = conexion.prepareStatement(sql);
          statement.setString(1, tfNombre.getText());
          statement.setString(2, tfApellido.getText());
          statement.setString(3, tfTelefono.getText());
          statement.setString(4, tfDireccion.getText());
          statement.setString(5, tfNit.getText());
          statement.execute();
          
      }catch(SQLException e){
          System.out.println(e.getMessage());
      }finally{
          try{
              if(statement != null){
                  statement.close();
              }
              if (conexion != null){
                   conexion.close();
              }
                  
          }catch(SQLException e){
            System.out.println(e.getMessage());
            
            
          }
      }
  }
  
  
    public void editarCliente(){
            
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarCliente(?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfClienteId.getText()));
        
        }catch(SQLException e){
            System.out.println(e.getMessage());
    }
  
  }
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

   
    
}
