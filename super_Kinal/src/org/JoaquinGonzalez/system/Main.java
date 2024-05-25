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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.JoaquinGonzalez.controller.FormClienteController;
import org.JoaquinGonzalez.controller.MenuClientesController;
import org.JoaquinGonzalez.controller.MenuPrincipalController;

/**
 *
 * @author Phant
 */
public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private final String URLVIEW = "/org/JoaquinGonzalez/view/";
    
    @Override
    public void start(Stage stage)  {
        this.stage = stage;
        stage.setTitle("super_Kinal app");
        menuPrincipalView();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
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
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 909, 599);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
        
         }
    public void menuClienteView(){
        try{
            MenuClientesController menuClientesView = (MenuClientesController)switchScene("MenuClientesView.fxml",1007 , 660);
            menuClientesView.setStage(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
            
            e.printStackTrace();
        }
    }
        public void formClienteView(){
            try{
                FormClienteController formClienteView = (FormClienteController)switchScene("FormClienteView.fxml",508,657);
                formClienteView.setStage(this);
            }catch(Exception e){
            System.out.println(e.getMessage());
     
        }
    }         
                
        public static void main(String[]args){
            launch(args);
        }
}

