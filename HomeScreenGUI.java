package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.application.Platform;

/**
 * Filename: HomeScreenGUI.java Project: A-Team Quiz Application Team: A-Team 27 Authors: Nicholas
 * Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * Home screen of the application
 */
public class HomeScreenGUI extends BorderPane {
  private Text numQuestionsText;

  public void updateValue(Main main) {
    numQuestionsText.setText(
        "Current number of questions on file: " + main.allQuestions.getTotalNumQuestions());
  }

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
    homescreenSave.setOnAction(e -> {
      primaryStage.setScene(main.saveScreen);
    });
    Button homescreenLoad = new Button("Load"); // load a questions list
    homescreenLoad.setOnAction(e -> {
      primaryStage.setScene(main.loadScreen);
    });
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
      main.updateAll();
    });

    VBox homeScreenWelcome = new VBox(welcome, welcomeSubtext, selectQuiz);
    setCenter(homeScreenWelcome);
    homeScreenWelcome.setAlignment(Pos.CENTER);
    numQuestionsText = new Text(
        "Current number of questions on file: " + main.allQuestions.getTotalNumQuestions());
    HBox numQuestionsBox = new HBox(numQuestionsText);
    numQuestionsBox.setAlignment(Pos.BOTTOM_LEFT);
    setBottom(numQuestionsBox);
   }

}
