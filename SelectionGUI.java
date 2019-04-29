package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Filename: SelectionGUI.java
 * Project: A-Team Quiz Application
 * Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * GUI which creates/selects a quiz
 */
public class SelectionGUI extends BorderPane {

  int selectedNumQuestions;
  ChoiceBox<String> topics;

  /**
   * Sets up the GUI for the quiz selection screen
   * 
   * @param main         Instance of the Main class which controls the GUIs and is the "Main" class
   * @param primaryStage The primary stage for the main GUI
   */
  public SelectionGUI(Main main, Stage primaryStage) {
    // main quiz screen

    // quiz me center button
    VBox centerBox = new VBox();
    TextField numQuestionsEntry = new TextField();
    Label numQuestionsPromptText = new Label("Enter number of Questions:");
    HBox numQuestionsSelectionBox = new HBox(numQuestionsPromptText, numQuestionsEntry);
    numQuestionsSelectionBox.setAlignment(Pos.CENTER);
    numQuestionsEntry.setPrefColumnCount(1);
    Button quizMe = new Button("Quiz Me!");
    quizMe.setOnAction(e -> {
      try {
        selectedNumQuestions = Integer.parseInt(numQuestionsEntry.getText());
        System.out.println("DEBUG: selected num Questions: " + selectedNumQuestions);
      } catch (NumberFormatException nE) {
        System.out.println("Please Input a valid number of questions");
      }
      // generate the quiz (stored as fields in Main.java)
      main.currQuiz = main.allQuestions.generateQuiz(main.selectedTopicsList, selectedNumQuestions);
      primaryStage.setScene(main.quizzingScreen);
    });

    centerBox.getChildren().addAll(quizMe, numQuestionsSelectionBox);
    centerBox.setAlignment(Pos.CENTER);
    setCenter(centerBox);

    // upper options panel
    Button returnToHome = new Button("Return to Home");
    returnToHome.setOnAction(e -> {
      primaryStage.setScene(main.home);
    });
    HBox quizHomeOptions = new HBox(10, returnToHome);
    quizHomeOptions.setPadding(main.buttonSpacing);

    setTop(quizHomeOptions);

    // upper right topics selection : display, add, remove. Also bottom right
    // selected topics
    Text selectedTopics = new Text("Selected Topics: ");
    topics = new ChoiceBox<String>();
    // TODO populate choiceBox with selected topics
    topics.getItems().addAll("option1", "option2", "option3");

    Button addTopic = new Button("Add Topic");
    addTopic.setOnAction(e -> {
      main.selectedTopicsList.add(topics.getValue());
      selectedTopics.setText("Selected Topics: " + main.selectedTopicsList.toString());
    });

    Button removeTopic = new Button("Remove Topic");
    removeTopic.setOnAction(e -> {
      main.selectedTopicsList.remove(topics.getValue());
      selectedTopics.setText("Selected Topics: " + main.selectedTopicsList.toString());
    });

    VBox topicSelectionBox = new VBox();
    topicSelectionBox.getChildren().addAll(topics, addTopic, removeTopic);
    quizHomeOptions.getChildren().add(topicSelectionBox);

    // bottom left display selected topics
    VBox bottomDisplay = new VBox();
    Text numQuestionsText = new Text("Current number of questions on file: " + main.allQuestions.getTotalNumQuestions());
    bottomDisplay.getChildren().addAll(numQuestionsText, selectedTopics);
    setBottom(bottomDisplay);
  }
}
