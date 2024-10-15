
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
import javafx.stage.Stage;
import userInterfaceTier.controllers.SignInController;

/**
 *
 * @author 2dam
 */
public class ClientApplication extends javafx.application.Application {

    public ClientApplication() {
    }

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
