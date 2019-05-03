package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// TODO make this pretty

public class SaveGUI extends BorderPane {

	Label filePathText; // label for the input
	TextField filePathInput; // input field for filepath
	boolean validFilePath = true; // display "invalid input" if bad input
	Text invalidInput = new Text("Invalid Input");
	String filepath;
	Button save;
	Button returnHome;

	public SaveGUI(Main main, Stage primaryStage) {
		filePathText = new Label("Input filepath for save: ");
		invalidInput.setVisible(false);
		filePathInput = new TextField();
		save = new Button("Save");
		save.setOnAction(e -> {
			try {
				filepath = filePathInput.getText();
				main.allQuestions.saveQuestionsToFile(filepath); // save
				validFilePath = true; // if reached, no exceptions (go to home menu)
			} catch (FileNotFoundException exc) { // TODO more specific exception handling
        System.out.println("File name not valid");
        validFilePath = false; 
        invalidInput.setVisible(true);
      } catch (IOException exc) {
        System.out.println("Error in saving file");
        validFilePath = false; 
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
		VBox centerBox = new VBox(filePathInputBox,save,invalidInput);
		setCenter(centerBox);
		setTop(returnHome);
	}
}
