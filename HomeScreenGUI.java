package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Filename: HomeScreenGUI.java
 * Project: A-Team Quiz Application
 * Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * Home screen of the application
 */
public class HomeScreenGUI extends BorderPane {

  /**
   * Sets up the GUI for the Home Screen
   * 
   * @param main         Instance of the Main class which controls the GUIs and is the "Main" class
   * @param primaryStage The primary stage for the main GUI
   */
  public HomeScreenGUI(Main main, Stage primaryStage) {
    // top options
    HBox homeScreenOptions = new HBox(10);
    Button homescreenExit = new Button("Exit"); // exit app
    homescreenExit.setOnAction(e -> {
      primaryStage.setScene(main.confirmationExit); // go to exit screen
    });

    Button homescreenSave = new Button("Save"); // save current questions list
    Button homescreenLoad = new Button("Load"); // load a questions list
    Button addQuestion = new Button("Add Question");
    addQuestion.setOnAction(e -> {
      primaryStage.setScene(main.addQuestionScreen);
    });

    homeScreenOptions.getChildren().addAll(homescreenExit, homescreenSave, homescreenLoad,
        addQuestion);
    setTop(homeScreenOptions);
    setPadding(main.buttonSpacing);

    // center welcome message and entry button
    Text welcome = new Text("Welcome to the Quiz Generator!");
    welcome.setId("welcomeText");
    Text welcomeSubtext = new Text("subtext");
    Button selectQuiz = new Button("Select Quiz");
    selectQuiz.setOnAction(e -> {
      primaryStage.setScene(main.quizHome);
    });

    VBox homeScreenWelcome = new VBox(welcome, welcomeSubtext, selectQuiz);
    setCenter(homeScreenWelcome);
    homeScreenWelcome.setAlignment(Pos.CENTER);

    // display number of questions loaded or selected in bottom left
    Text numQuestionsText = new Text("Current number of questions: " + main.allQuestions.totalNumQuestions);
    HBox numQuestionsBox = new HBox(numQuestionsText);
    numQuestionsBox.setAlignment(Pos.BOTTOM_LEFT);
    setBottom(numQuestionsBox);
  }

}
