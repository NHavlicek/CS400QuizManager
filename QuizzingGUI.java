package application;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Filename: QuizzingGUI.java Project: A-Team Quiz Application Team: A-Team 27 Authors: Nicholas
 * Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * GUI which quizzes the user
 */
public class QuizzingGUI extends BorderPane {
  Button submitAnswer;
  Label questionBodyLabel;
  Label questionTopicLabel;
  RadioButton[] choiceIsCorrect;
  ToggleGroup tg;
  // TODO image

  /**
   * Sets up the GUI for Quizzing screen
   * 
   * @param main         Instance of the Main class which controls the GUIs and is the "Main" class
   * @param primaryStage The primary stage for the main GUI
   */
  public QuizzingGUI(Main main, Stage primaryStage) {

    // return home button
    HBox topRow = new HBox(10);
    VBox topSide = new VBox(0);
    topSide.setPadding(main.buttonSpacing);

    // question info input

    questionBodyLabel = new Label("Question: ");
    questionTopicLabel = new Label("Topic: ");

    topSide.getChildren().add(questionBodyLabel);
    topSide.getChildren().add(questionTopicLabel);

    // question options
    VBox midPart = new VBox(5);
    midPart.setPadding(main.buttonSpacing);

    Label selectRightAnswer = new Label("Select Correct Answer: ");
    midPart.getChildren().add(selectRightAnswer);

    tg = new ToggleGroup();
    choiceIsCorrect = new RadioButton[5];
    for (int i = 0; i < 5; i++) {
      HBox questionRow = new HBox(10);
      Label choiceText = new Label("Choice " + (i + 1));
      choiceIsCorrect[i] = new RadioButton();

      choiceIsCorrect[i].setId(i + ""); // Saves which answer it's associated with
      choiceIsCorrect[i].setToggleGroup(tg);

      questionRow.getChildren().add(choiceText);
      questionRow.getChildren().add(choiceIsCorrect[i]);

      midPart.getChildren().add(questionRow);
    }

    // submit question button
    submitAnswer = new Button("Submit");
    submitAnswer.setOnAction(e -> {
      AnswerChoice answer = getAnswerChoice(main);

      if (answer != null && main.currQuiz.checkAnswer(answer)) {
        System.out.println("Correct!");
      }
      // Next Question if answer isn't null
      // if quiz.isOver, return control to Main.java or a results screen
      // updateFields(nextQuestion);
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
    ArrayList<Question> questions = quiz.getQuestions();
    Question question = quiz.getCurrQuestion();

    if (tg.getSelectedToggle() != null) {
      button = (RadioButton) tg.getSelectedToggle();
      try {
        int choice = Integer.parseInt(button.getId());
        return question.getChoices().get(choice);
      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }

  public void updateFields(Question currQuestion) {
    // update the fields (e.g. choice1Text, question body, etc);
    questionBodyLabel = new Label("Question: " + currQuestion.getQuestionText()); // Make These to get methods
    questionTopicLabel = new Label("Topic: " + currQuestion.getTopic());
    
    tg = new ToggleGroup();
    choiceIsCorrect = new RadioButton[currQuestion.getChoices().size()];
    for (int i = 0; i < choiceIsCorrect.length; i++) {
      HBox questionRow = new HBox(10);
      Label choiceText = new Label(currQuestion.getChoices().get(i).choiceText);//Use getter
      choiceIsCorrect[i] = new RadioButton();

      choiceIsCorrect[i].setId(i + ""); // Saves which answer it's associated with
      choiceIsCorrect[i].setToggleGroup(tg);
    }
  }

}
