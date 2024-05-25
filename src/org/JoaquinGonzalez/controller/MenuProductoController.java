package org.JoaquinGonzalez.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import org.JoaquinGonzalez.dao.Conexion;
import org.JoaquinGonzalez.model.Producto;
import org.JoaquinGonzalez.system.Main;


public class MenuProductoController implements Initializable {
    private Main stage;

    
    private static Connection conexion = null;
    private static PreparedStatement statement = null; 
    private static ResultSet resulSet = null;
    private List<File> files = null;
    
    
    
    @FXML
    Button btnCargar, btnBuscar,btnRegresar,btnGuardar;
    @FXML
    TextField tfNombreProducto, tfProductoId,tfCantidad,tfPrecio,tfPrecioU,tfPrecioM;
    @FXML
    ImageView imgCargar, imgMostrar;
    @FXML
    Label lblNombreProducto;
    @FXML     
    TextArea taDescripcion;
    @FXML
    ComboBox cmbDistribuidor, cmbCategoria;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cargarcmbDistribuidor(); 
       cargarcmbCategoria(); 
        
    }
    public void cargarcmbDistribuidor(){
           cmbDistribuidor.getItems().add("En Proceso");
           cmbDistribuidor.getItems().add("Finalizado");
       }
     public void cargarcmbCategoria(){
           cmbCategoria.getItems().add("En Proceso");
           cmbCategoria.getItems().add("Finalizado");
       }   
    
   
  @FXML 
    public void handleButtonAction(ActionEvent event){
       try{

           if(event.getSource() == btnCargar){
            agregarProducto();
        }else if (event.getSource() == btnBuscar){
            Producto producto = buscarProducto();
            if(producto != null){
                lblNombreProducto.setText(producto.getNombreProducto());
               InputStream file = producto.getImage().getBinaryStream();
               Image image = new Image(file);
               imgMostrar.setImage(image);
            } 
        } else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
            }
       }catch(Exception e){
            System.out.println(e.getMessage());
       }
 
    }

    @FXML
    public void handleOnDrag(DragEvent event){
         if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        } 
    }    
    @FXML
    public void handleOnDrop(DragEvent event){
        try{
            files = event.getDragboard().getFiles();
            FileInputStream file = new FileInputStream (files.get(0));
            Image image = new Image(file);
            imgCargar.setImage(image);
        }catch(Exception e){
            System.out.println(e.getMessage());
     
      }      
    }

    public void agregarProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql  ="call sp_agregarProductos(?,?)";
            statement  = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreProducto.getText());
            
            
            InputStream img = new FileInputStream(files.get(0));
            statement.setBinaryStream(2, img);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                    
                }
                if (conexion != null){
                    conexion.close();
                }
            }catch(Exception e){
            System.out.println(e.getMessage());
            
        }
    } 
 }
    
    
     
    public Producto buscarProducto(){
        Producto producto = null;
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfProductoId.getText()));
            resulSet = statement.executeQuery();
            
            if(resulSet.next()){
                int productoId = resulSet.getInt("");
                String nombreProducto = resulSet.getString("nombre");
                String descripcionProducto = resulSet.getString("descripcionProducto");
                int cantidadStock = resulSet.getInt("cantidadStock");
                double precionVentaUnitario = resulSet.getDouble("precionVentaUnitario");
                double precioVentaMayor = resulSet.getDouble("precioVentaMayor");
                double precioCompra = resulSet.getDouble("precioCompra");
                int distribuidorId = resulSet.getInt("distribuidorId");
                int categoriaProductos = resulSet.getInt("categoriaProductos");
                Blob image = resulSet.getBlob("image");
                
                producto = new Producto(productoId,nombreProducto,descripcionProducto,cantidadStock,precionVentaUnitario,precioVentaMayor,precioCompra,distribuidorId,categoriaProductos,image);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       return producto; 
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
} 
     
 

