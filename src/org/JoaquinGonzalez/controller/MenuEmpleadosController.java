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
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.JoaquinGonzalez.dao.Conexion;
import org.JoaquinGonzalez.model.Cargos;
import org.JoaquinGonzalez.model.Empleados;
import org.JoaquinGonzalez.model.Encargados;
import org.JoaquinGonzalez.system.Main;
import org.JoaquinGonzalez.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Phant
 */
public class MenuEmpleadosController implements Initializable {
          private Main stage;
    
    private int op;
    
    private Connection conexion = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    
    @FXML
    TextField tfEmpleadoId, tfNombre, tfApellido, tfSueldo, tfHoraEntrada, tfHoraSalida, tfEmpleadoBuscarId;
        
    @FXML
    ComboBox cmbCargo, cmbEncargado;
    
    @FXML
    TableView tblEmpleados;
    
    @FXML
    TableColumn colEmpleadoId, colNombre, colApellido, colSueldo, colHoraEntrada, colHoraSalida, colCargo, colEncargadoId;
    
    @FXML
    Button btnGuardar, btnVaciar, btnRegresar, btnEliminar, btnBuscar,btnEmpleados,btnAgregar,btnEditar;
    
   @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfEmpleadoId.getText().equals("")){
                agregarEmpleado();
                cargarDatos();
            }else{
                editarEmpleado();
                cargarDatos();
            }
        }else if(event.getSource() == btnVaciar){
            vaciarForm();
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarEmpleado(((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getEmpleadoId());
                cargarDatos();
            }
        }else if(event.getSource() == btnBuscar){
            tblEmpleados.getItems().clear();
            
            if(tfEmpleadoBuscarId.getText().equals("")){
                cargarDatos();
            }else{
                op = 3;
                cargarDatos();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }    
        public void cargarDatos(){
        if(op == 3){
            tblEmpleados.getItems().add(buscarEmpleado());
            op = 0;
        }else{
        tblEmpleados.setItems(listarEmpleados());
            colEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleados, Integer>("empleadoId"));
            colNombre.setCellValueFactory(new PropertyValueFactory<Empleados, String>("nombreEmpleado"));
            colApellido.setCellValueFactory(new PropertyValueFactory<Empleados, String>("apellidoEmpleado"));
            colSueldo.setCellValueFactory(new PropertyValueFactory<Empleados, Double>("sueldo"));
            colHoraEntrada.setCellValueFactory(new PropertyValueFactory<Empleados, Time>("horaEntrada"));
            colHoraSalida.setCellValueFactory(new PropertyValueFactory<Empleados, Time>("horaSalida"));
            colCargo.setCellValueFactory(new PropertyValueFactory<Empleados, String>("cargo"));
            colEncargadoId.setCellValueFactory(new PropertyValueFactory<Empleados, Integer>("encargado"));
        }

    }
         public void vaciarForm(){
        tfEmpleadoId.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfSueldo.clear();
        tfHoraEntrada.clear();
        tfHoraSalida.clear();
        cmbCargo.getSelectionModel().clearSelection();
        cmbEncargado.getSelectionModel().clearSelection();
    }
    
    @FXML
    public void cargarForm(){
        Empleados e = (Empleados)tblEmpleados.getSelectionModel().getSelectedItem();
        Time horaEntrada = e.getHoraEntrada();
        Time horaSalida = e.getHoraSalida();
        if(e != null){
            tfEmpleadoId.setText(Integer.toString(e.getEmpleadoId()));
            tfNombre.setText(e.getNombreEmpleado());
            tfApellido.setText(e.getApellidoEmpleado());
            tfSueldo.setText(Double.toString(e.getSueldo()));
            tfHoraEntrada.setText(horaEntrada.toString());
            tfHoraSalida.setText(horaSalida.toString());
            cmbCargo.getSelectionModel().select(obtenerIndexCargo());
            cmbEncargado.getSelectionModel().select(obtenerIndexEncargado());
        }
    }
    
        public int obtenerIndexCargo(){
        int index = 0;
        String cargoTbl = ((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getCargo();
        for(int i = 0 ; i <= cmbCargo.getItems().size() ; i++){
            String cargoCmb = cmbCargo.getItems().get(i).toString();
            
            if(cargoTbl.equals(cargoCmb)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    public int obtenerIndexEncargado(){
        int index = 0;
        String encargadoTbl = ((Empleados)tblEmpleados.getSelectionModel().getSelectedItem()).getEncargado();
        for(int i = 0 ; i <= cmbEncargado.getItems().size() ; i++){
            String encargadoCmb = cmbEncargado.getItems().get(i).toString();
            
            if(encargadoTbl.equals(encargadoCmb)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
    
    public ObservableList<Empleados> listarEmpleados(){
        ArrayList<Empleados> empleado = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                double sueldo = resultSet.getDouble("sueldo");
                Time horaEntrada = resultSet.getTime("horaEntrada");
                Time horaSalida = resultSet.getTime("horaSalida");
                String cargo = resultSet.getString("cargo");
                String encargado = resultSet.getString("encargado");
                
                empleado.add(new Empleados(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargo, encargado));
            }
        }catch(SQLException e){
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
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(empleado);
    }
    
    public ObservableList<Cargos> listarCargos(){
        ArrayList<Cargos> cargo = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCargos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int cargoId = resultSet.getInt("cargoId");
                String nombreCargo = resultSet.getString("nombreCargo");  
                String descripcionCargo = resultSet.getString("descripcionCargo");
                
                cargo.add(new Cargos(cargoId, nombreCargo, descripcionCargo));
            }
        }catch(SQLException e){
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
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(cargo);
    }
    
    public ObservableList<Encargados> listarEncargados(){
        ArrayList<Encargados> encargado = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                
                encargado.add(new Encargados(empleadoId));
            }
        }catch(SQLException e){
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
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(encargado);
    }
    
     public void agregarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarEmpleado(?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfApellido.getText());
            statement.setDouble(3, Double.parseDouble(tfSueldo.getText()));
            statement.setTime(4, Time.valueOf(tfHoraEntrada.getText()));
            statement.setTime(5, Time.valueOf(tfHoraSalida.getText()));
            statement.setInt(6, ((Cargos)cmbCargo.getSelectionModel().getSelectedItem()).getCargoId());
            statement.setInt(7, ((Encargados)cmbEncargado.getSelectionModel().getSelectedItem()).getEncargadoId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement!= null){
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
    
    public void editarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarEmpleado(?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfApellido.getText());
            statement.setDouble(3, Double.parseDouble(tfSueldo.getText()));
            statement.setTime(5, Time.valueOf(tfHoraEntrada.getText()));
            statement.setTime(6, Time.valueOf(tfHoraSalida.getText()));
            statement.setInt(7, ((Cargos)cmbCargo.getSelectionModel().getSelectedItem()).getCargoId());
            statement.setInt(8, ((Encargados)cmbEncargado.getSelectionModel().getSelectedItem()).getEncargadoId());
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
    public void asignarEncargado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_asignarEncargado(?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoId.getText()));
            statement.setInt(2, ((Encargados)cmbEncargado.getSelectionModel().getSelectedItem()).getEncargadoId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement!= null){
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
    
    public void eliminarEmpleado(int empId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarEmpleado(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, empId);
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

    public Empleados buscarEmpleado(){
        Empleados empleado = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarEmpleado(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoBuscarId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                double sueldo = resultSet.getDouble("sueldo");
                Time horaEntrada = resultSet.getTime("horaEntrada");
                Time horaSalida = resultSet.getTime("horaSalida");
                String cargoId = resultSet.getString("cargoId");
                String encargadoId = resultSet.getString("encargadoId");
                
                empleado = new Empleados(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId, encargadoId);
            }
            
        }catch(SQLException e){
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
                
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
     
        return empleado;
    }
    
    
    
    
    
    
    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }
    
}
