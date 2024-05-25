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
import org.JoaquinGonzalez.model.Categoria;
import org.JoaquinGonzalez.system.Main;

/**
 * FXML Controller class
 *
 * @author Phant
 */
public class MenuCategoriaProductoController implements Initializable {
       private Main stage;
        
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
     

    
       @FXML
       TextField tfCargoId; 
       @FXML
       TableColumn  colCategoriaPId, colNombreC,colDescripcionP;
       @FXML
       Button btnRegresar,  btnBuscar,btnCategorias,btnGuardar; 
       @FXML
       TableView tblCategoria;
   
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
   
    }  
    
    public void cargarDatos (){
        tblCategoria.setItems(ListarCategoriaProductos());
        colCategoriaPId.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("categoriaProductoId"));
        colNombreC.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombreCategoria"));
        colDescripcionP.setCellValueFactory(new PropertyValueFactory<Categoria, String>("descripcionCargo"));
  }
    
       @FXML
  public void handleButtonAction(ActionEvent event){
      if(event.getSource()== btnRegresar){
          stage.menuPrincipalView();

      }else if(event.getSource() == btnGuardar){
        if(tfCargoId.getText().equals("")){
            cargarDatos();
      }
     }
  }
    
    public ObservableList<Categoria> ListarCategoriaProductos() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "Call sp_ListarCategoriaProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int categoriaId = resultSet.getInt("categoriaProductoId");
                String NombreP = resultSet.getString("nombreCategoria");
                String DescripcionP = resultSet.getString("descripcionCargo");
                categorias.add(new Categoria(categoriaId, NombreP, DescripcionP));
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
        return FXCollections.observableList(categorias);
    }
    
    public Categoria BuscarCategoriaProductos(){
        Categoria categorias = null;    
    try{
        conexion = Conexion.getInstance().obtenerConexion();
        String sql = "call sp_BuscarCargos(?)";
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(tfCargoId.getText()));
        resultSet = statement.executeQuery();
        
        if(resultSet.next()){
            int categoriaId = resultSet.getInt("categoriaProductoId");
            String NombreP = resultSet.getString("nombreCategoria");
            String descripcionP = resultSet.getString("descripcionCargo");
           
            categorias = new Categoria(categoriaId, NombreP, descripcionP);
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
        return categorias;
   }   
    

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
}
