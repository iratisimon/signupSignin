/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceTier.controllers;

import clientBusinessLogic.ClientFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logicalModel.interfaces.Signable;
import logicalModel.model.User;

/**
 *
 * @author Olaia
 */
public class MainWindowController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TitledPane tpMenu;

    @FXML
    private AnchorPane anchorPane2;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnExit;

    @FXML
    private TextField tfFullName;

    @FXML
    private Label lblFullName;

    @FXML
    private TextField tfEmail;

    @FXML
    private Label lblEmail;

    @FXML
    private TextField tfMobile;

    @FXML
    private Label lblMobile;

    @FXML
    private TextField tfActive;

    @FXML
    private Label lblActive;

    private Stage stage;
    private Stage signInStage;
   //private Signable signable;

    public void initStage(Parent root, User userSignedIn) {
        Scene scene = new Scene(root);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("MainWindow");
        Image icon = new Image(getClass().getResourceAsStream("/resources/images/catrina.png"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        tfFullName.isFocused();
        btnExit.setOnAction(this::handleExit);
        btnLogOut.setOnAction(this::handleLogOut);
        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void handleExit(ActionEvent event) {
        // Cierra la aplicación
        Platform.exit();

    }

    public void setSignInStage(Stage signInStage) {
        this.signInStage = signInStage;
    }

    private void handleLogOut(ActionEvent event) {
            stage.close();
            signInStage.show();
     
    }

}
