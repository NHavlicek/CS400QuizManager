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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GUI screen that is used to load questions from a json file. Only supports input for file paths
 * (no file browsing yet). Called by HomeScreenGUI to load questions, and can return to the
 * HomeScreenGUI upon button push.
 * 
 * @author Spencer Runde, Nick Havlicek, Murad Jaber, Kevin Kim, Dung Vo
 *
 */
public class LoadGUI extends BorderPane {

  Label filePathText; // label for the input
  TextField filePathInput; // input field for filepath to load
  boolean validFilePath = true; // display "invalid input" if bad input
  Text invalidInput = new Text("Invalid Input");
  String filepath;
  Button load;
  Button returnHome;

  /**
   * constructor for a loadGUI instance
   * 
   * @param main         the instance of Main that runs this program
   * @param primaryStage the primaryStage of Application in JavaFX
   */
  public LoadGUI(Main main, Stage primaryStage) {
    filePathText = new Label("Input filepath for load: ");
    invalidInput.setVisible(false);
    filePathInput = new TextField();
    load = new Button("Load");
    load.setOnAction(e -> {
      try {
        filepath = filePathInput.getText();
        main.allQuestions.loadQuestions(filepath, main);
        validFilePath = true; // if reached, no exceptions (go to home menu)
      } catch (FileNotFoundException exc) { // TODO more specific exception handling
        System.out.println("File not found");
        validFilePath = false; 
        invalidInput.setVisible(true);
      } catch (ParseException exc) {
        System.out.println("Failed to parse the input file");
        validFilePath = false; 
        invalidInput.setVisible(true);
      } catch (IOException exc) {
        System.out.println("Error in loading file");
        validFilePath = false; 
        invalidInput.setVisible(true);
      }
      if (validFilePath) {
        primaryStage.setScene(main.home); // return home if input valid
      }

      main.updateAll();
    });
    returnHome = new Button("Return to Home");
    returnHome.setOnAction(e -> {
      primaryStage.setScene(main.home);
      invalidInput.setVisible(false);
      // return home
    });

    VBox filePathInputBox = new VBox(filePathText, filePathInput);
    VBox centerBox = new VBox(filePathInputBox, load, invalidInput);
    setCenter(centerBox);
    setTop(returnHome);
  }
}
