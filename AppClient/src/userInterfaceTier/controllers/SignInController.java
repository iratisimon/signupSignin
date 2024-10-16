/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
            //Se abrirá la ventana Sign Up.
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("userInterfaceTier/view/SignUpView.fxml"));
            Parent root = (Parent) loader.load();
            SignUpController controller = (SignUpController) loader.getController();

            Stage stage = new Stage();
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.setResizable(false);
        stage.show();
    }

}
