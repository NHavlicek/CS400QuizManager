package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Filename: SelectionGUI.java Project: A-Team Quiz Application Team: A-Team 27 Authors: Nicholas
 * Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * GUI which creates/selects a quiz
 */
public class SelectionGUI extends BorderPane {

  int selectedNumQuestions;
  Button returnToHome;
  ChoiceBox<String> topics;
  HBox quizHomeOptions;
  Text selectedTopics;
  Button addTopic;
  Button removeTopic;
  VBox topicSelectionBox;
  VBox bottomDisplay;
  Text numQuestionsText;
  Text numQuestionsMatchingSelectedTopicsText;
  int numQuestionsMatchingSelectedTopics = 0;

  /**
   * changes appropriate displays when actions are taken either here or in other GUIs. Updated
   * fields are the displayed topic list (e.g. a question with a new topic is added), the selected
   * topics list (a new topic is selected or unselected by the user), and the number of questions
   * matching the selected topics (when a user adds or removes topics this updates accordingly).
   * 
   * @param main
   */
  public void updateValue(Main main) {
    List<String> list = new ArrayList<String>();
    list.addAll(main.totalTopicsList);
    ObservableList<String> topicList = FXCollections.observableList(list);
    topics.setItems(topicList);
    // TODO do we need these next few lines?
    selectedTopics.setText("Selected Topics: " + main.selectedTopicsList.toString());
    topicSelectionBox.getChildren().setAll(topics, addTopic, removeTopic);
    numQuestionsText.setText(
        "Current number of questions on file: " + main.allQuestions.getTotalNumQuestions());
  }

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
        // generate the quiz (stored as fields in Main.java)
        main.currQuiz = main.allQuestions.generateQuiz(main.selectedTopicsList,
            selectedNumQuestions);
        // ensure quiz has at least one question and was generated
        if (main.currQuiz != null && main.currQuiz.getQuestions().size() > 0) {
          primaryStage.setScene(main.quizzingScreen);
          main.quizMeGUI.updateFields(main.currQuiz.getCurrQuestion());
        } else {
          System.out.println("Error: Invalid number of questions: " + selectedNumQuestions);
        }
      } catch (NumberFormatException nE) {
        System.out.println("Please Input a valid number of questions");
      }
    });

    centerBox.getChildren().addAll(quizMe, numQuestionsSelectionBox);
    centerBox.setAlignment(Pos.CENTER);
    setCenter(centerBox);

    // upper options panel
    returnToHome = new Button("Return to Home");
    returnToHome.setOnAction(e -> {
      primaryStage.setScene(main.home);
    });
    quizHomeOptions = new HBox(10, returnToHome);
    quizHomeOptions.setPadding(main.buttonSpacing);

    setTop(quizHomeOptions);

    // upper right topics selection : display, add, remove. Also bottom right
    // selected topics
    selectedTopics = new Text("Selected Topics: ");
    topics = new ChoiceBox<String>();
    if (main.totalTopicsList.size() > 0) {
      topics.getItems().addAll(main.totalTopicsList);
    }

    addTopic = new Button("Add Topic");
    addTopic.setOnAction(e -> {
      if (topics.getValue() != null) {
        main.selectedTopicsList.add(topics.getValue());
        selectedTopics.setText("Selected Topics: " + main.selectedTopicsList.toString());
        updateMatchingQuestions(main);
      }
    });

    removeTopic = new Button("Remove Topic");
    removeTopic.setOnAction(e -> {
      main.selectedTopicsList.remove(topics.getValue());
      selectedTopics.setText("Selected Topics: " + main.selectedTopicsList.toString());
      updateMatchingQuestions(main);
    });

    topicSelectionBox = new VBox();
    topicSelectionBox.getChildren().addAll(topics, addTopic, removeTopic);
    quizHomeOptions.getChildren().add(topicSelectionBox);

    // bottom left display selected topics
    bottomDisplay = new VBox();
    numQuestionsText = new Text(
        "Current number of questions on file: " + main.allQuestions.getTotalNumQuestions());
    numQuestionsMatchingSelectedTopicsText = new Text(
        "Current number of questions matching selected topics: "
            + numQuestionsMatchingSelectedTopics);
    bottomDisplay.getChildren().addAll(numQuestionsMatchingSelectedTopicsText, numQuestionsText,
        selectedTopics);
    setBottom(bottomDisplay);

  }

  /**
   * utilizes the findAllQuestionsWithTopic() method in QuestionBank to list the number of questions
   * matching all the selected topics
   * 
   * @param main instance of the Main class that contains all data structure instances.
   */
  private void updateMatchingQuestions(Main main) {
    numQuestionsMatchingSelectedTopics = 0;
    String[] selectedTopicsArray = main.selectedTopicsList.toArray(new String[0]);
    for (String topic : selectedTopicsArray) {
      numQuestionsMatchingSelectedTopics += main.allQuestions.findAllQuestionsWithTopic(topic)
          .size();
    }
    numQuestionsMatchingSelectedTopicsText
        .setText("Current number of questions matching selected topics: "
            + numQuestionsMatchingSelectedTopics);
  }

}
