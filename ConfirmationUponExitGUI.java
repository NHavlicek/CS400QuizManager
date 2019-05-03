//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Final Project: QuizManager
//
// Files: Main.java, AddQuestion.java, AnswerChoice.java, 
//    ConfimationUponExitGUI.java, HomeScreen.java, LoadGUI.java, Question.java
//    QuestionBank.java, Quiz.java, QuizzingGUI, SaveGUI.java, SelectionGUI.java
//    application.css
//
// Course: CS 400 Spring 2019
//
// Author: Dung Vo, Murad Jaber, Nick Havlicek, Kevin Kim, Spencer Runde
// Email: dvo2@wisc.edu, mjaber2@wisc.edu, nhavlicek@wisc.edu, runde3@wisc.edu
//        kkim434@wisc.edu
// Lecturer's Name: Andrew Kuemmel, Debra Deppler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: N/A
// Online Sources: N/A
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

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
    //Exits program
    Button exitNoSaveButton = new Button("Exit without saving");
    exitNoSaveButton.setOnAction(e -> {
      primaryStage.close();
    });
    //Takes user back to main screen and lets them save
    Button saveAndExitButton = new Button("Save");
    saveAndExitButton.setOnAction(e -> {
    	
      primaryStage.setScene(main.saveScreen);;
    });
    confirmExitButtons.getChildren().addAll(saveAndExitButton, exitNoSaveButton);
    
    //Returns user to main screen
    Button confirmationExitReturnHome = new Button("Return to Home");
    confirmationExitReturnHome.setOnAction(e -> {
      primaryStage.setScene(main.home);
    });
    setTop(confirmationExitReturnHome);


  }

}
