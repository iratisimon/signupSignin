
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import userInterfaceTier.controllers.SignInController;

/**
 * The ClientApplication class is the entry point for the JavaFX application.
 * It initializes the application and sets up the primary stage.
 * 
 * This class extends javafx.application.Application and is responsible for loading the
 * SignInView FXML file and initializing the SignInController.
 * 
 * @author Irati
 */
public class ClientApplication extends javafx.application.Application {
    /**
     * Default constructor for the ClientApplication class.
     */
    public ClientApplication() {
    }
    
    /**
     * The start method is called when the application is launched. It sets up the primary stage
     * by loading the SignInView FXML file and initializing the SignInController.
     * 
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException if there is an error loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/userInterfaceTier/view/SignInView.fxml"));
            Parent root = (Parent) loader.load();

            SignInController controller = ((SignInController)loader.getController());

            controller.setStage(stage);

            controller.initStage(root);
       } catch (IOException ex) {
            Logger.getLogger(ClientApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The main method is the entry point of the application.
     * 
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
