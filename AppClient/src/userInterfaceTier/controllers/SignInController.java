/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logicalModel.model.User;

/**
 *
 * @author 2dam
 */
public class SignInController {
    
    @FXML
    private Label label;
    
    private Stage stage;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            //Se abrirá la ventana Sign Up de manera modal.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("userInterfaceTier/view/SignUpView.fxml"));
            Parent root = (Parent) loader.load();
            SignUpController controller = (SignUpController) loader.getController();
            //Ventana modal.
            Stage modalStage = new Stage();
            controller.setStage(modalStage);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      

    void setStage(Stage modalStage) {
        this.stage = modalStage;
    }

    void initStage(Parent root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
