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
import javafx.scene.control.cell.PropertyValueFactory;
import org.JoaquinGonzalez.dao.Conexion;
import org.JoaquinGonzalez.model.Cliente;
import org.JoaquinGonzalez.system.Main;
 
/**
* FXML Controller class
*
* @author Phant
*/
public class MenuClientesController implements Initializable {
    private Main stage;

    private static Connection conexion;
    private static  PreparedStatement statement;
    private static  ResultSet resultSet;
       
    
    @FXML
        TableView tblClientes;
        @FXML
        TableColumn colClienteId,colNombre,colApellido,colTelefono,colDireccion,colNit; 
        @FXML
        Button btnRegresar,btnAgregar,btnEditar;
       
        @FXML
        public void handleButtonAction(ActionEvent event){
            if(event.getSource() == btnRegresar){
                stage.menuPrincipalView();
            }else if (event.getSource() == btnAgregar){
                stage.formClienteView();
        }else if (event.getSource() == btnEditar){
                stage.formClienteView();
        }
            }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();    

    }    
    
    public void cargarDatos(){
        tblClientes.setItems(listarClientes());
        colClienteId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("clienteId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        colNit.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nit"));
    }
    
    
    public ObservableList<Cliente>listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarClientes()";
            statement = conexion.prepareStatement(sql);
            resultSet = (ResultSet) statement.executeQuery();
            while(resultSet.next()){
                int clienteId = resultSet.getInt("clienteId");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                String nit = resultSet.getString("nit");
                clientes.add(new Cliente(clienteId,nombre,apellido, telefono,direccion,nit));
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
    public void setStage(Main stage) {
        this.stage = stage;
    }
 
    public Main getStage() {
        return stage;
    }
 
    
    }