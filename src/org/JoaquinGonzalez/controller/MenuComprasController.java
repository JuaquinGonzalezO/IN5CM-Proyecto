/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import org.JoaquinGonzalez.model.Compra;
import org.JoaquinGonzalez.system.Main;

/**
 * FXML Controller class
 *
 * @author Phant
 */
public class MenuComprasController implements Initializable {
       private Main stage;
               
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    
       @FXML
       TextField tfCompraId,tfFechaCompra,tfTotalCompra; 
       @FXML
       TableColumn  colCompraId, colFechaC,colTotalC;
       @FXML
       Button btnRegresar,btnBuscar,btnAgregar,btnEditar,btnEliminar; 
       @FXML
       TableView tblCompras;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     cargarDatos();
        
  }
    public void cargarDatos (){
        tblCompras.setItems(ListarCompras());
        colCompraId.setCellValueFactory(new PropertyValueFactory<Compra, Integer>("compraId"));
        colFechaC.setCellValueFactory(new PropertyValueFactory<Compra, String>("fechaCompra"));
        colTotalC.setCellValueFactory(new PropertyValueFactory<Compra, String>("totalCompra"));
     }
    
    public void agregarCompras(){
      
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_AgregarCompras(?,?)";
       statement = conexion.prepareStatement(sql);
       statement.setString(1,tfFechaCompra.getText());
       statement.setString(2,tfTotalCompra.getText());
       statement.execute();
      
       }catch(SQLException e){
        System.out.println(e.getMessage());
       }finally{
       try{
               
            if(statement != null){
                  statement.close();
               }
            if(conexion != null){
                 conexion.close();
                }
      
       }catch(SQLException e){
        System.out.println(e.getMessage());
       }
     }
   }
    
       @FXML
  public void handleButtonAction(ActionEvent event){
      if(event.getSource()== btnRegresar){
          stage.menuPrincipalView();
           } else if(event.getSource() == btnAgregar){
            if(!tfFechaCompra.getText().equals("") && !tfTotalCompra.getText().equals("")){
                agregarCompras();
                cargarDatos();
            }
        } else if(event.getSource() == btnEditar){
            
        if(!tfFechaCompra.getText().equals("") && !tfTotalCompra.getText().equals("")){
        editarCargos();
        CargosDTO.getCargosDTO().setCargos(null);
        cargarDatos();
        }
            } else if (event.getSource() == btnEliminar){
          eliminarCargo(((Cargos)tblCargos.getSelectionModel().getSelectedItem()).getCargoId());
          cargarDatos();
      
            
            }else if (event.getSource() == btnBuscar){
            tblCompras.getItems().clear();
            tfCompraId.getText().equals("");
            tblCompras.getItems().add(buscarCargos());
        }
    }
    
  public ObservableList<Compra> ListarCompras() {
        ArrayList<Compra> Compras = new ArrayList<>();
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "Call sp_ListarCompras()";
            statement = conexion.prepareStatement(sql);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int compraId = resultSet.getInt("compraId");
                Date fechaC = resultSet.getDate("fechaCompra");
                Double totalC = resultSet.getDouble("totalCompra");
                Compras.add(new Compra (compraId, fechaC, totalC));
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
        return FXCollections.observableList(Compras);
    }  
    public Compra BuscarCompras(){
        Compra compra = null;    
    try{
        conexion = Conexion.getInstance().obtenerConexion();
        String sql = "call sp_BuscarCompras(?)";
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(tfCompraId.getText()));
        resultSet = statement.executeQuery();
        
        if(resultSet.next()){
             int compraId = resultSet.getInt("compraId");
                Date fechaC = resultSet.getDate("fechaCompra");
                Double totalC = resultSet.getDouble("totalCompra");
            compra = new Compra(compraId, fechaC, totalC);
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
        return compra;
   }    
  
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
