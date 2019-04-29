package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadGUI extends BorderPane {
	
	Label filePathText; // label for the input
	TextField filePathInput; // input field for filepath to load
	boolean validFilePath = true; // display "invalid input" if bad input
	Text invalidInput = new Text("Invalid Input");
	String filepath;
	Button load;
	Button returnHome;

	public LoadGUI(Main main, Stage primaryStage) {
		filePathText = new Label("Input filepath for load: ");
		invalidInput.setVisible(false);
		filePathInput = new TextField();
		load = new Button("Load");
		load.setOnAction(e -> {
			try {
				filepath = filePathInput.getText();
				System.out.println("DEBUG: filepath: " + filepath);
				main.allQuestions.loadQuestions(); // TODO loadQuestions needs file input from here
				validFilePath = true; // if reached, no exceptions (go to home menu)
			} catch (Exception exc) { // TODO more specific exception handling
				System.out.println("DEBUG: exception in Load");
				validFilePath = false; // exceptions come from parsing filepath or from filenotfound
				invalidInput.setVisible(true);
			}
			if (validFilePath) {
				primaryStage.setScene(main.home); // return home if input valid
			}
		});
		returnHome = new Button("Return to Home");
		returnHome.setOnAction(e -> {
			primaryStage.setScene(main.home);
			; // return home
		});
		
		VBox filePathInputBox = new VBox(filePathText,filePathInput);
		VBox centerBox = new VBox(filePathInputBox,load,invalidInput);
		setCenter(centerBox);
		setTop(returnHome);
	}
}
