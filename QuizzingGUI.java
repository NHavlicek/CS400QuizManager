package application;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Filename: QuizzingGUI.java Project: A-Team Quiz Application Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * GUI which quizzes the user
 */
public class QuizzingGUI extends BorderPane {	
	Text questionBodyText;
	Text choice1Text;
	Text choice2Text;
	Text choice3Text;
	Text choice4Text;
	Text choice5Text;
	Button submitAnswer;
	// TODO image

	/**
	 * Sets up the GUI for Quizzing screen
	 * 
	 * @param main         Instance of the Main class which controls the GUIs and is
	 *                     the "Main" class
	 * @param primaryStage The primary stage for the main GUI
	 */
	public QuizzingGUI(Main main, Stage primaryStage) {
		submitAnswer = new Button("Submit");
		submitAnswer.setOnAction(e->{
			// check current answer
			// if quiz.isOver, return control to Main.java or a results screen
			//updateFields(nextQuestion);
		});
		// TODO implement
		// 1. display the question body text
		// 2. display the choices text
		// 3. display RadioButtons or similar option to select correct option
		// 4. display option for an image

	}
	
	public void updateFields(Question currQuestion) {
		// update the fields (e.g. choice1Text, question body, etc);
	}

}
