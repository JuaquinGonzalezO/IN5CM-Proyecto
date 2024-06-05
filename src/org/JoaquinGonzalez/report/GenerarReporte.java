package org.JoaquinGonzalez.report;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javafx.stage.Stage;
import org.JoaquinGonzalez.dao.Conexion;

/**
 *
 * @author Phant
 */
public class GenerarReporte {
    private static GenerarReporte instance;
    
    private static Connection conexion = null;
    
    private GenerarReporte() {
}
    public static GenerarReporte getInstance(){
        if (instance == null ){
            instance = new GenerarReporte();
        }
        return instance;
    }
    
    public void generarFactura(int i){
        try{
            conexion = Conexion. getInstance().obtenerConexion();
            
            Map<String, Object>parametros = new HashMap<>();
            parametros.put("cambiar parametro", 1);
            
            Stage reportStage = new Stage();
            
            
          
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}