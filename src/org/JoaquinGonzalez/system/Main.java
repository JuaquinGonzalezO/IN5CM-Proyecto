/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.system;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.JoaquinGonzalez.controller.FormClienteController;
import org.JoaquinGonzalez.controller.MenuCargoController;
import org.JoaquinGonzalez.controller.MenuCategoriaProductoController;
import org.JoaquinGonzalez.controller.MenuClientesController;
import org.JoaquinGonzalez.controller.MenuComprasController;
import org.JoaquinGonzalez.controller.MenuDetalleFacturaController;
import org.JoaquinGonzalez.controller.MenuDistribuidoresController;
import org.JoaquinGonzalez.controller.MenuEmpleadosController;
import org.JoaquinGonzalez.controller.MenuFacturaController;
import org.JoaquinGonzalez.controller.MenuPrincipalController;
import org.JoaquinGonzalez.controller.MenuProductoController;
import org.JoaquinGonzalez.controller.MenuPromocionController;


import org.JoaquinGonzalez.controller.MenuTicketSoporteController;


/**
 *
 * @author Phant
 */
public class Main extends Application {
    private final String URLVIEW = "/org/JoaquinGonzalez/view/";
    private Stage stage;
    private Scene scene;
   
    
    @Override
    public void start(Stage stage)  {
        this.stage = stage;
        stage.setTitle("Super_Kinal App");
        Image icon = new Image("org/JoaquinGonzalez/image/Icono.png");
        stage.getIcons().add(icon);
        menuPrincipalView();
        stage.show();
    }

    
    public Initializable switchScene (String fxmlName, int width, int height)throws Exception {
        Initializable resultdo = null ;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName );
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        
        scene = new Scene ((AnchorPane)loader.load(file),width,height);
        stage.setScene(scene);
        stage.sizeToScene();
       
       resultdo = (Initializable)loader.getController();
       return resultdo;
    }
    
    public void menuPrincipalView(){
        try{   
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 909, 595);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
        
         }
    public void menuClienteView(){
        try{
            MenuClientesController menuClientesView = (MenuClientesController)switchScene("MenuClientesView.fxml",994 , 658);
            menuClientesView.setStage(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
            
            
        }
    }
        public void formClienteView(int op){
            try{
                FormClienteController formClienteView = (FormClienteController)switchScene("FormClienteView.fxml",500,651);
                formClienteView.setOp(op);
                formClienteView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }         
        
    public void menuTicketSoporteView(){
            try{
                MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController)switchScene("MenuTicketSoporteView.fxml", 1042, 686);
                menuTicketSoporteView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }             
    

    public void menuProductoView(){
            try{
                MenuProductoController menuProductoView = (MenuProductoController) switchScene ("MenuProductoView.fxml", 1400, 749);
                menuProductoView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }         

    public void menuCargoView(){
            try{
                MenuCargoController menuCargoView = (MenuCargoController) switchScene ("MenuCargosView.fxml", 732, 518);
                menuCargoView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }      
    
    public void menuCompraView(){
            try{
                MenuComprasController menuCompraView = (MenuComprasController) switchScene ("MenuComprasView.fxml", 700, 500);
                menuCompraView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }      
    public void menuCategoriaProductoView(){
            try{
                MenuCategoriaProductoController menuCategoriaProductoView = (MenuCategoriaProductoController) switchScene ("MenuCategoriaProductosView.fxml", 700, 450);
                menuCategoriaProductoView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }            
        
     
    public void menuDistribuidorView(){
            try{
                MenuDistribuidoresController menuDistribuidorView = (MenuDistribuidoresController) switchScene ("MenuDistribuidoresView.fxml", 700, 450);
                menuDistribuidorView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }  
    
    
    public void menuEmpleadosView(){
            try{
                MenuEmpleadosController menuEmpleadosView = (MenuEmpleadosController) switchScene ("MenuEmpleadosView.fxml", 1190, 742);
                menuEmpleadosView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }
      
         public void menuFacturaView(){
            try{
                MenuFacturaController menuFacturaView = (MenuFacturaController) switchScene ("MenuFacturaView.fxml", 750, 450);
                menuFacturaView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }  
         
         public void menuPromocionView(){
            try{
                MenuPromocionController menuPromocionView = (MenuPromocionController) switchScene ("MenuPromocionView.fxml", 1200, 750);
                menuPromocionView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }   
        public void menuDetalleFacturaView(){
             try{
                MenuDetalleFacturaController menuDetalleFacturaView = (MenuDetalleFacturaController) switchScene ("MenuDetalleFacturaView.fxml", 836, 536);
                menuDetalleFacturaView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }   
        
         
        public static void main(String[]args){
            launch(args);
        }
}

