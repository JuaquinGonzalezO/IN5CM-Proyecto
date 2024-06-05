/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.JoaquinGonzalez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.JoaquinGonzalez.system.Main;

/**
 * FXML Controller class
 *
 * @author Phant
 */
public class MenuDetalleFacturaController implements Initializable {

    private Main Stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Main getStage() {
        return Stage;
    }

    public void setStage(Main stage) {
        this.Stage = stage;
    }
    
}
