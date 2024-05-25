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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.JoaquinGonzalez.dao.Conexion;
import org.JoaquinGonzalez.dto.CargosDTO;
import org.JoaquinGonzalez.model.Cargos;
import org.JoaquinGonzalez.system.Main;

/**
 * FXML Controller class
 *
 * @author Phant
 */
public class MenuCargoController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
        
       @FXML
       TextField tfbuscarId,tfNombreCargo,tfDescripcionCargo,tfCargoId; 
       @FXML
       TableColumn  colCargoId, colNombreCargo,colDescripcionCargo;
       @FXML
       Button btnRegresar, btnBuscar,btnAgregar,btnEditar,btnEliminar; 
       @FXML
       TableView tblCargos;
      
   
       @Override
    
   public void initialize(URL location, ResourceBundle resources) {
       cargarDatos();
     
    }      
       
  public void cargarDatos (){
        tblCargos.setItems(ListarCargos());
        colCargoId.setCellValueFactory(new PropertyValueFactory<Cargos, Integer>("cargoId"));
        colNombreCargo.setCellValueFactory(new PropertyValueFactory<Cargos, String>("nombreCargo"));
        colDescripcionCargo.setCellValueFactory(new PropertyValueFactory<Cargos, String>("descripcionCargo"));
  }
   public void agregarCargos(){
      
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_AgregarCargos(?,?)";
       statement = conexion.prepareStatement(sql);
       statement.setString(1,tfNombreCargo.getText());
       statement.setString(2,tfDescripcionCargo.getText());
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
            if(!tfNombreCargo.getText().equals("") && !tfDescripcionCargo.getText().equals("")){
                agregarCargos();
                cargarDatos();
            }
        } else if(event.getSource() == btnEditar){
            
        if(!tfNombreCargo.getText().equals("") && !tfDescripcionCargo.getText().equals("")){
        editarCargos();
        CargosDTO.getCargosDTO().setCargos(null);
        cargarDatos();
        }
            } else if (event.getSource() == btnEliminar){
          eliminarCargo(((Cargos)tblCargos.getSelectionModel().getSelectedItem()).getCargoId());
          cargarDatos();
      
            
            }else if (event.getSource() == btnBuscar){
            tblCargos.getItems().clear();
            tfbuscarId.getText().equals("");
            tblCargos.getItems().add(buscarCargos());
        }
    }
  
  public void editarCargos(){
    
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_EditarCargos(?,?,?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, Integer.parseInt(tfCargoId.getText()));
       statement.setString(2, tfNombreCargo.getText());
       statement.setString(3, tfDescripcionCargo.getText());
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
   public void eliminarCargo(int carId){
       try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_EliminarCargos(?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, carId);
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
  
   public ObservableList<Cargos> ListarCargos() {
        
        ArrayList<Cargos> cargos = new ArrayList<>();
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "Call sp_ListarCargos()";
            statement = conexion.prepareStatement(sql);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int cargoId = resultSet.getInt("cargoId");
                String nombreCargo = resultSet.getString("nombreCargo");
                String descripcionCargo = resultSet.getString("descripcionCargo");
                cargos.add(new Cargos(cargoId, nombreCargo, descripcionCargo));
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
        return FXCollections.observableList(cargos);
    }
   
  
  
  public Cargos buscarCargos(){
         Cargos cargos = null;
    try{
       conexion = Conexion.getInstance().obtenerConexion();
       String sql = "call sp_BuscarCargos(?)";
       statement = conexion.prepareStatement(sql);
       statement.setInt(1, Integer.parseInt(tfbuscarId.getText()));
       resultSet = statement.executeQuery();
       
       if(resultSet.next()){
           int cargoId = resultSet.getInt("cargoId");
           String nombreCargo = resultSet.getString("NombreCargo");
           String descripcionCargo = resultSet.getString("DescripcionCargo");
          
           cargos = new Cargos(cargoId , nombreCargo, descripcionCargo);
       }  
      
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
            if(resultSet != null){
                resultSet.close();
            }
      
       }catch(SQLException e){
        System.out.println(e.getMessage());
       }  
       }
     return    cargos;
    }

  
   
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
   
  }
        

