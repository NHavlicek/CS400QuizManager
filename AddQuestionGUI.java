package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Filename: AddQuestionGUI.java
 * Project: A-Team Quiz Application
 * Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * GUI screen to add a question
 */
public class AddQuestionGUI extends BorderPane {
  
  /**
   * Sets up the GUI for the Add questions screen
   * 
   * @param main         Instance of the Main class which controls the GUIs and is the "Main" class
   * @param primaryStage The primary stage for the main GUI
   */
  public AddQuestionGUI(Main main, Stage primaryStage) {

    // return home button
    HBox topRow = new HBox(10);
    VBox topSide = new VBox(0);
    topSide.setPadding(main.buttonSpacing);
    
    //Top buttons
    Button returnToHome = new Button("Return to Home");
    returnToHome.setOnAction(e -> {
      primaryStage.setScene(main.home);
    });
    topRow.getChildren().add(returnToHome);
    topSide.getChildren().add(topRow);

    // question info input
    HBox questionInfoBox = new HBox(10);
    
    Label questionBodyInputLabel = new Label("Question body:");
    Label questionTopicInputLabel = new Label("Question topic:");
    TextArea questionBodyInput = new TextArea();
    TextField questionTopicInput = new TextField();

    questionBodyInput.setMaxHeight(50);
    
    questionInfoBox.getChildren().add(questionTopicInputLabel);
    questionInfoBox.getChildren().add(questionTopicInput);

    topSide.getChildren().add(questionBodyInputLabel);
    topSide.getChildren().add(questionBodyInput);
    topSide.getChildren().add(questionInfoBox);
    
    // question options
    VBox midPart = new VBox(5);
    midPart.setPadding(main.buttonSpacing);
   
    Label selectRightAnswer = new Label("Select Correct Answer: ");
    midPart.getChildren().add(selectRightAnswer);
    
    ToggleGroup tg = new ToggleGroup();
    for (int i = 0; i < 5; i++) {
      HBox questionRow = new HBox(10);
      Label choiceText = new Label("Choice " + (i + 1));
      TextField choiceInput = new TextField();
      RadioButton choiceIsCorrect = new RadioButton();
      
      choiceIsCorrect.setToggleGroup(tg);
      choiceInput.setMinWidth(290);

      questionRow.getChildren().add(choiceText);
      questionRow.getChildren().add(choiceInput);
      questionRow.getChildren().add(choiceIsCorrect);
      
      midPart.getChildren().add(questionRow);
    }
    
    // submit question button
    Button submitQuestion = new Button("Submit Question");
    submitQuestion.setOnAction(e -> {
      String questionBody = questionBodyInput.getText();
      String topic = questionTopicInput.getText();
      // TODO create functionality of button
      // Create a new question
      // Return to home screen or offer chance to submit a new question
    });
    
    setTop(topSide);
    setCenter(midPart);
    setBottom(submitQuestion);
  }
}
