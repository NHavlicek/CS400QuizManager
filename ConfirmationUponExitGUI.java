package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Filename: ConfirmationUponExitGUI.java
 * Project: A-Team Quiz Application
 * Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * Exit screen GUI
 */
public class ConfirmationUponExitGUI extends BorderPane {

  /**
   * Sets up the GUI for the confirmation screen when exiting the application
   * 
   * @param main         Instance of the Main class which controls the GUIs and is the "Main" class
   * @param primaryStage The primary stage for the main GUI
   */
  public ConfirmationUponExitGUI(Main main, Stage primaryStage) {
    Text confirmationMessage = new Text("Would you like to save your current quiz file?");
    HBox confirmExitButtons = new HBox(10);
    confirmExitButtons.setPadding(main.buttonSpacing);
    VBox confirmExitCenterBox = new VBox(confirmationMessage, confirmExitButtons);
    setCenter(confirmExitCenterBox);
    confirmExitCenterBox.setAlignment(Pos.CENTER);
    confirmExitButtons.setAlignment(Pos.CENTER);
    Button exitNoSaveButton = new Button("Exit without saving");
    exitNoSaveButton.setOnAction(e -> {
      primaryStage.close();
    });
    Button saveAndExitButton = new Button("Save");
    saveAndExitButton.setOnAction(e -> {
    	
      primaryStage.setScene(main.saveScreen);;
    });
    confirmExitButtons.getChildren().addAll(saveAndExitButton, exitNoSaveButton);

    Button confirmationExitReturnHome = new Button("Return to Home");
    confirmationExitReturnHome.setOnAction(e -> {
      primaryStage.setScene(main.home);
    });
    setTop(confirmationExitReturnHome);


  }

}
