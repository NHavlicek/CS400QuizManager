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

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Filename: AddQuestionGUI.java Project: A-Team Quiz Application Team: A-Team 27 Authors: Nicholas
 * Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * GUI screen to add a question
 */
public class AddQuestionGUI extends BorderPane {
  private ToggleGroup tg;
  private TextField[] choiceInput;
  private String imagePath;

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

    // Top buttons
    Button returnToHome = new Button("Return to Home");
    returnToHome.setOnAction(e -> {
      primaryStage.setScene(main.home);
    });
    topRow.getChildren().add(returnToHome);
    topSide.getChildren().add(topRow);

    // Add Image
    HBox imageRow = new HBox(10);
    
    // Image itself
    ImageView image = new ImageView();
    image.setFitHeight(200);
    image.setFitWidth(200);
    
    // Button to prompt the user to add it
    Button addImage = new Button("Add Image");
    addImage.setOnAction(e -> {
      // Dialog
      TextInputDialog dialog = new TextInputDialog("Enter Path");
      dialog.setTitle("Enter Path");
      dialog.setHeaderText("Enter Path To An Image");
      
      Optional<String> result = dialog.showAndWait();
      
      if (result.isPresent()) {
        imagePath = result.get();
        image.setImage(new Image(imagePath));
      }
    });

    imageRow.getChildren().add(image);
    imageRow.getChildren().add(addImage);
    topSide.getChildren().add(imageRow);
    
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

    tg = new ToggleGroup();
    choiceInput = new TextField[5];
    for (int i = 0; i < 5; i++) {
      HBox questionRow = new HBox(10);
      Label choiceText = new Label("Choice " + (i + 1));
      choiceInput[i] = new TextField();
      RadioButton choiceIsCorrect = new RadioButton();

      choiceIsCorrect.setToggleGroup(tg);
      choiceInput[i].setMinWidth(290);

      questionRow.getChildren().add(choiceText);
      questionRow.getChildren().add(choiceInput[i]);
      questionRow.getChildren().add(choiceIsCorrect);

      midPart.getChildren().add(questionRow);
    }

    // submit question button
    Button submitQuestion = new Button("Submit Question");
    submitQuestion.setOnAction(e -> {
      String questionBody = questionBodyInput.getText();
      String topic = questionTopicInput.getText();
      ArrayList<AnswerChoice> answers = new ArrayList<AnswerChoice>();
      
      //Loops through getting the user input for question
      for (int i = 0; i < tg.getToggles().size(); i++) {
        AnswerChoice choice;
        String questionText = choiceInput[i].getText();
        boolean isCorrect = tg.getSelectedToggle() == tg.getToggles().get(i);

        if (questionText != null) {
          choice = new AnswerChoice(questionText, isCorrect);
          answers.add(choice);
        }
        
        choiceInput[i].clear();//Cleared after it was read to make clean up easier
      }

      if (questionBody.length() > 0 && topic.length() > 0 && answers.size() == 5) {
        Question question;
        if (image.getImage() != null) {
          question = new Question(questionBody, topic, answers, SwingFXUtils.fromFXImage(image.getImage(), null));
        } else {
          question = new Question(questionBody, topic, answers, null);
        }
        
        main.totalTopicsList.add(topic);
        main.allQuestions.addQuestion(question);
        
        main.updateAll();
      }
      
      // Clean up
      image.setImage(null);
      questionBodyInput.clear();
      questionTopicInput.clear();
      
      if (tg.getSelectedToggle() != null) {
        tg.getSelectedToggle().setSelected(false);
      }
      //Returns to main GUI
      primaryStage.setScene(main.home);
    });

    setTop(topSide);
    setCenter(midPart);
    setBottom(submitQuestion);
  }
}
