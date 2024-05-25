/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.JoaquinGonzalez.dao.Conexion;
import org.JoaquinGonzalez.model.Distribuidor;
import org.JoaquinGonzalez.system.Main;

/**
 * FXML Controller class
 *
 * @author Phant
 */
public class MenuDistribuidoresController implements Initializable {
 private Main stage;
        
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
   
    
    
       @FXML
       TextField tfDistribuidorId; 
       @FXML
       TableColumn  colDistribuidorId, colDireccion,colNitD,colTelefono,colWeb;
       @FXML
       Button btnRegresar,  btnBuscar,btnDistribuidores,btnGuardar; 
       @FXML
       TableView tblDistribuidor;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         cargarDatos();
    }    

    public void cargarDatos (){
        tblDistribuidor.setItems(ListarDistribuidores());
        colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Distribuidor, Integer>("nombreDistribuidor"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Distribuidor, String>("direccionDistribuidor"));
        colNitD.setCellValueFactory(new PropertyValueFactory<Distribuidor, String>("nitDistribuidor"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Distribuidor, String>("telefonoDistribuidor"));
        colWeb.setCellValueFactory(new PropertyValueFactory<Distribuidor, String>("web"));
  }
    
      @FXML
  public void handleButtonAction(ActionEvent event){
      if(event.getSource()== btnRegresar){
          stage.menuPrincipalView();

      }else if(event.getSource() == btnGuardar){
        if(tfDistribuidorId.getText().equals("")){
            cargarDatos();
      }
     }
  }    
    
        public ObservableList<Distribuidor> ListarDistribuidores() {
        ArrayList<Distribuidor> distribuidores = new ArrayList<>();
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "Call sp_ListarDistribuidores()";
            statement = conexion.prepareStatement(sql);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                String nombreDistribuidor = resultSet.getString("nombreDistribuidor");
                String direccionDistribuidor = resultSet.getString("direccionDistribuidor");
                String nitDistribuidor = resultSet.getString("nitDistribuidor");
                String telefonoDistribuidor = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
                distribuidores.add(new Distribuidor(nombreDistribuidor, direccionDistribuidor, nitDistribuidor,telefonoDistribuidor,web));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(distribuidores);
    }
      
    public Distribuidor BuscarCategoriaProductos(){
        Distribuidor distribuidores = null;    
    try{
        conexion = Conexion.getInstance().obtenerConexion();
        String sql = "call sp_BuscarCargos(?)";
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(tfDistribuidorId.getText()));
        resultSet = statement.executeQuery();
        
        if(resultSet.next()){
                String nombreDistribuidor = resultSet.getString("nombreDistribuidor");
                String direccionDistribuidor = resultSet.getString("direccionDistribuidor");
                String nitDistribuidor = resultSet.getString("nitDistribuidor");
                String telefonoDistribuidor = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
            distribuidores = new Distribuidor(nombreDistribuidor, direccionDistribuidor, nitDistribuidor,telefonoDistribuidor,web);
        }
        
    }catch(SQLException e) {
        System.out.println(e.getMessage());
    }finally{
        try{
      if(resultSet != null){
          resultSet.close();  
      }   
      if(statement != null){
          statement.close();
      }
      if(conexion != null){ 
          conexion.close();
      }
    
    }catch(SQLException e) {
         System.out.println(e.getMessage());
       }   
    }    
        return distribuidores;
   }   
    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
