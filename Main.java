package application;

//import application;

import java.util.HashSet;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

/**
 * Filename: Main.java Project: A-Team Quiz Application Team: A-Team 27 Authors:
 * Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * Main GUI which sets the scenes shown to the user
 */
public class Main extends Application {

	/**
	 * scenes explained: 1. home : the first screen seen upon opening the
	 * application. Save, load files, add questions, enter the actual application.
	 * Can also exit.
	 * 
	 * 2. quizHome : The scene entered when the user hits "select quiz". A quiz
	 * customization screen. User can select topics and see what is displayed.
	 * Enters the quiz upon hitting "quiz me"
	 * 
	 * 3. startQuizzing : the actual quizzing part. Shows the current question,
	 * multiple answer options, a button to advance to the next question (hidden
	 * before answer given?), an exit button, and when answered an indication of
	 * correctness. Optional extras: question number info (number correct, number
	 * left, etc.)
	 * 
	 * 4. confirmationExit : checks if the user wants to save before exiting.
	 * 
	 * 5. addQuestionsScreen: has text input fields for manually-submitted
	 * questions. Displays the topics currently in the question bank
	 * 
	 * TODO more to come
	 */
	public Scene home, quizHome, startQuizzing, confirmationExit, addQuestionScreen, quizzingScreen,
	saveScreen, loadScreen;
	public QuestionBank allQuestions;
	public Insets buttonSpacing = new Insets(15, 12, 15, 12); // spacing for buttons (standard)
	public HashSet<String> totalTopicsList = new HashSet<String>(); // all topics among all questions
	public HashSet<String> selectedTopicsList = new HashSet<String>(); // only topics selected by user
	public Quiz currQuiz;

	/**
	 * Creates the main GUI and controls the GUIs
	 * 
	 * @param primaryStage The main stage shown
	 */
	@Override
	public void start(Stage primaryStage) {		
		allQuestions = new QuestionBank(); // initialize the questionBank

		// home screen
		HomeScreenGUI homeScreen = new HomeScreenGUI(this, primaryStage);
		home = new Scene(homeScreen, 400, 400);

		// main quiz screen
		SelectionGUI quizEntry = new SelectionGUI(this, primaryStage);
		quizHome = new Scene(quizEntry, 400, 400);

		// confirmation upon exit
		ConfirmationUponExitGUI confirmExit = new ConfirmationUponExitGUI(this, primaryStage);
		confirmationExit = new Scene(confirmExit, 400, 400);

		// GUI during quizzing TODO implement
		QuizzingGUI quizMeGUI = new QuizzingGUI(this, primaryStage);
		quizzingScreen = new Scene(quizMeGUI, 400, 400);

		// add question screen
		AddQuestionGUI addQ = new AddQuestionGUI(this, primaryStage);
		addQuestionScreen = new Scene(addQ, 400, 400);
		
		// save questions screen
		SaveGUI saveQuestionsGUI = new SaveGUI(this, primaryStage);
		saveScreen = new Scene(saveQuestionsGUI, 400, 400);
		
		// load questions screen
		LoadGUI loadQuestionsGUI = new LoadGUI(this, primaryStage);
		loadScreen = new Scene(loadQuestionsGUI, 400, 400);

		// display the home screen first
		primaryStage.setScene(home);
		primaryStage.show();
	}

	/**
	 * Launches the application
	 * 
	 * @param args Arguments passed to the launch
	 */
	public static void main(String[] args) {
		System.out.println("before launch");
		launch(args);
		System.out.println("after launch");
	}
	
}
