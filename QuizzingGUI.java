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

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Filename: QuizzingGUI.java Project: A-Team Quiz Application Team: A-Team 27 Authors: Nicholas
 * Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * GUI which quizzes the user. Utilizes the QuestionBank from main to obtain Questions. Screens are
 * "advanced" by calling the next Question in the Quiz and updating all relevant fields (question
 * text, answers, correctness, etc).
 */
public class QuizzingGUI extends BorderPane {
  int currentQuestionNumber = 1;
  Text previousQuestionCorrect;
  Text previousQuestionIncorrect;
  Button submitAnswer;
  Label questionBodyLabel;
  Label questionTopicLabel;
  RadioButton[] choiceIsCorrect;
  Label[] choiceText;
  ToggleGroup tg;
  ImageView questionImageLocation;
  Image questionImage;

  /**
   * Sets up the GUI for Quizzing screen
   * 
   * @param main         Instance of the Main class which controls the GUIs and is the "Main" class
   * @param primaryStage The primary stage for the main GUI
   */
  public QuizzingGUI(Main main, Stage primaryStage) {

    // return home button
    VBox topSide = new VBox(0);
    topSide.setPadding(main.buttonSpacing);

    // previous question correctness
    previousQuestionCorrect = new Text("Previous answer was correct!");
    previousQuestionCorrect.setFill(Color.GREEN);
    previousQuestionIncorrect = new Text("Previous answer was incorrect.");
    previousQuestionIncorrect.setFill(Color.RED);
    previousQuestionCorrect.setVisible(false);
    previousQuestionIncorrect.setVisible(false);

    // question info input
    questionImageLocation = new ImageView();
    questionImageLocation.setFitHeight(200);
    questionImageLocation.setFitWidth(200);
    questionBodyLabel = new Label("Question " + currentQuestionNumber + ": ");
    questionBodyLabel.setWrapText(true);
    questionTopicLabel = new Label("Topic: ");

    topSide.getChildren().add(questionImageLocation);
    topSide.getChildren().add(questionBodyLabel);
    topSide.getChildren().add(questionTopicLabel);
    topSide.getChildren().add(previousQuestionCorrect);
    topSide.getChildren().add(previousQuestionIncorrect);

    // question options
    VBox midPart = new VBox(5);
    midPart.setPadding(main.buttonSpacing);

    Label selectRightAnswer = new Label("Select Correct Answer: ");
    midPart.getChildren().add(selectRightAnswer);

    tg = new ToggleGroup();
    choiceIsCorrect = new RadioButton[5];
    choiceText = new Label[5];
    for (int i = 0; i < 5; i++) {
      HBox questionRow = new HBox(10);
      choiceText[i] = new Label("Choice " + (i + 1));
      choiceIsCorrect[i] = new RadioButton();

      choiceIsCorrect[i].setId(i + ""); // Saves which answer it's associated with
      choiceIsCorrect[i].setToggleGroup(tg);

      questionRow.getChildren().add(choiceText[i]);
      questionRow.getChildren().add(choiceIsCorrect[i]);

      midPart.getChildren().add(questionRow);
    }

    // submit question button
    submitAnswer = new Button("Submit");
    submitAnswer.setOnAction(e -> {
      AnswerChoice answer = getAnswerChoice(main);
      if (answer != null) {
        if (main.currQuiz.checkAnswer(answer)) {
          main.currQuiz.numCorrect++;
          previousQuestionCorrect.setVisible(true);
          previousQuestionIncorrect.setVisible(false);
        } else {
          previousQuestionCorrect.setVisible(false);
          previousQuestionIncorrect.setVisible(true);
        }
        if (main.currQuiz.quizOver()) {
          previousQuestionCorrect.setVisible(false);
          previousQuestionIncorrect.setVisible(false);
          Alert totalScore = new Alert(AlertType.INFORMATION);
          totalScore.setTitle("Total Score");
          totalScore.setContentText("You got " + main.currQuiz.numCorrect
              + " questions correct out of " + main.currQuiz.numQuestions + " possible ("
              + (int) main.currQuiz.numCorrect * 100 / main.currQuiz.numQuestions + "%)");
          totalScore.showAndWait();
          primaryStage.setScene(main.home);
        } else {
          updateFields(main.currQuiz.getNextQuestion());
        }
      }
    });

    setTop(topSide);
    setCenter(midPart);
    setBottom(submitAnswer);

    // TODO implement
    // 4. display option for an image

  }

  private AnswerChoice getAnswerChoice(Main main) {
    RadioButton button;
    Quiz quiz = main.currQuiz;
    Question question = quiz.getCurrQuestion();

    if (tg.getSelectedToggle() != null) {
      button = (RadioButton) tg.getSelectedToggle();
      try {
        int choice = Integer.parseInt(button.getId());
        return question.getChoices().get(choice);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
      }
    }
    return null;
  }

  public void updateFields(Question currQuestion) {
    // update the fields (e.g. choice1Text, question body, etc);
    if (currQuestion.getImage() != null) {
      questionImageLocation.setVisible(true);
      questionImageLocation.setImage(SwingFXUtils.toFXImage(currQuestion.getImage(), null));
    } else {
      questionImageLocation.setVisible(false);
    }
    questionBodyLabel
        .setText("Question " + +currentQuestionNumber + ": " + currQuestion.getQuestionText()); // Make
                                                                                                // These
                                                                                                // to
                                                                                                // get
                                                                                                // methods
    questionTopicLabel.setText("Topic: " + currQuestion.getTopic());
    currentQuestionNumber++; // increment the display for the current question upon advancing
                             // questions

    if (tg.getSelectedToggle() != null) {
      tg.getSelectedToggle().setSelected(false);
    }

    for (int i = 0; i < choiceIsCorrect.length; i++) {
      if (i < currQuestion.getChoices().size()) {
        choiceText[i].setText(currQuestion.getChoices().get(i).getChoiceText());
      } else {
        choiceText[i].setText("");
      }
      choiceIsCorrect[i] = new RadioButton();

      choiceIsCorrect[i].setId(i + ""); // Saves which answer it's associated with
      choiceIsCorrect[i].setToggleGroup(tg);
    }
  }

}
