package org.JoaquinGonzalez.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import org.JoaquinGonzalez.model.Cliente;
import org.JoaquinGonzalez.model.Empleados;
import org.JoaquinGonzalez.model.Facturas;
import org.JoaquinGonzalez.system.Main;
import org.JoaquinGonzalez.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author Phant
 */
public class MenuFacturaController implements Initializable {
   private Main Stage;
    
    private int op;
    
    private Connection conexion = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    
    @FXML
    TextField tfFacturaId, tfFecha, tfHora, tfTotal, tfFacturaBuscarId;
        
    @FXML
    ComboBox cmbCliente, cmbEmpleado;
    
    @FXML
    TableView tblFacturas;
    
    @FXML
    TableColumn colFacturaId, colFecha, colHora, colCliente, colEmpleado, colTotal;
    
    @FXML
    Button btnGuardar, btnVaciar, btnRegresar, btnEliminar, btnBuscar;
    
    @FXML
     public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            Stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfFacturaId.getText().equals("")){
                agregarFactura();
                cargarDatos();
            }else{
                editarFactura();
                cargarDatos();
            }
        }else if(event.getSource() == btnVaciar){
            
        }else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(404).get() == ButtonType.OK){
                eliminarFactura(((Facturas)tblFacturas.getSelectionModel().getSelectedItem()).getFacturaId());
                cargarDatos();
            }
        }else if(event.getSource() == btnBuscar){
            tblFacturas.getItems().clear();
            
            if(tfFacturaBuscarId.getText().equals("")){
                cargarDatos();
            }else{
                op = 3;
                cargarDatos();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cmbCliente.setItems(listarClientes());
        cmbEmpleado.setItems(listarEmpleados());
    }
    
     public void cargarDatos(){
        if(op == 3){
            tblFacturas.getItems().add(buscarFactura());
            op = 0;
        }else{
        tblFacturas.setItems(listarFacturas());
            colFacturaId.setCellValueFactory(new PropertyValueFactory<Facturas, Integer>("facturaId"));
            colFecha.setCellValueFactory(new PropertyValueFactory<Facturas, Date>("fecha"));
            colHora.setCellValueFactory(new PropertyValueFactory<Facturas, Time>("hora"));
            colCliente.setCellValueFactory(new PropertyValueFactory<Facturas, String>("cliente"));
            colEmpleado.setCellValueFactory(new PropertyValueFactory<Facturas, String>("empleado"));
            colTotal.setCellValueFactory(new PropertyValueFactory<Facturas, Double>("total"));
        }
    }    
     
     
     
    
     public int obtenerIndexCliente(){
        int index = 0;
        String clienteTbl = ((Facturas)tblFacturas.getSelectionModel().getSelectedItem()).getCliente();
        for(int i = 0 ; i <= cmbCliente.getItems().size() ; i++){
            String clienteCmb = cmbCliente.getItems().get(i).toString();
            
            if(clienteTbl.equals(clienteCmb)){
                index = i;
                break;
            }
        }
        
        return index;
    }
       
    public int obtenerIndexEmpleado(){
        int index = 0;
        String empleadoTbl = ((Facturas)tblFacturas.getSelectionModel().getSelectedItem()).getEmpleado();
        for(int i = 0 ; i <= cmbEmpleado.getItems().size() ; i++){
            String empleadoCmb = cmbEmpleado.getItems().get(i).toString();
            
            if(empleadoTbl.equals(empleadoCmb)){
                index = i;
                break;
            }
        }
        
        return index;
    }
    
   public ObservableList<Facturas> listarFacturas(){
        ArrayList<Facturas> factura = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarFacturas()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int facturaId = resultSet.getInt("facturaId");
                Date fecha = resultSet.getDate("fecha");
                Time hora = resultSet.getTime("hora");
                String cliente = resultSet.getString("cliente");
                String empleadoId = resultSet.getString("empleadoId");
                double total = resultSet.getDouble("total");
                factura.add(new Facturas(facturaId, fecha, hora, cliente, empleadoId, total));
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
        
        return FXCollections.observableList(factura);
    }

    public ObservableList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarClientes()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int clienteId = resultSet.getInt("clienteId");
                String nombre = resultSet.getString("nombre");  
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String nit = resultSet.getString("nit");
                String direccion = resultSet.getString("direccion");
                
                clientes.add(new Cliente(clienteId, nombre, apellido, telefono, nit, direccion));
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
        
        return FXCollections.observableList(clientes);
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
    
     public void agregarFactura(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarFactura(?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(tfFecha.getText()));
            statement.setTime(2, Time.valueOf(tfHora.getText()));
            statement.setInt(3, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(4, ((Empleados)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.setDouble(5, Double.parseDouble(tfTotal.getText()));
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
    
    public void editarFactura(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarFactura(?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfFacturaId.getText()));
            statement.setDate(2, Date.valueOf(tfFecha.getText()));
            statement.setTime(3, Time.valueOf(tfHora.getText()));
            statement.setInt(4, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(5, ((Empleados)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.setDouble(6, Double.parseDouble(tfTotal.getText()));
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
    
    public void eliminarFactura(int facId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarFactura(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, facId);
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
    
    public Facturas buscarFactura(){
        Facturas factura = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarFactura(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfFacturaBuscarId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int facturaId = resultSet.getInt("facturaId");
                Date fecha = resultSet.getDate("fecha");
                Time hora = resultSet.getTime("hora");
                String clienteId = resultSet.getString("clienteId");
                String empleadoId = resultSet.getString("empleadoId");
                double total = resultSet.getDouble("total");
                
                factura = new Facturas(facturaId, fecha, hora, clienteId, empleadoId, total);
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
     
        return factura;
    }
    public Main getStage() {
        return Stage;
    }

    public void setStage(Main Stage) {
        this.Stage = Stage;
    }
    
}
