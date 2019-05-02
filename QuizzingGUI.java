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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Filename: QuizzingGUI.java Project: A-Team Quiz Application Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * GUI which quizzes the user
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
	 * @param main         Instance of the Main class which controls the GUIs and is
	 *                     the "Main" class
	 * @param primaryStage The primary stage for the main GUI
	 */
	public QuizzingGUI(Main main, Stage primaryStage) {

		// return home button
		VBox topSide = new VBox(0);
		topSide.setPadding(main.buttonSpacing);
		
		// previous question correctness
		previousQuestionCorrect = new Text("Answer was correct!");
		previousQuestionCorrect.setFill(Color.GREEN);
		previousQuestionIncorrect = new Text("Answer was incorrect.");
		previousQuestionIncorrect.setFill(Color.RED);
		previousQuestionCorrect.setVisible(false);
		previousQuestionIncorrect.setVisible(false);

		// question info input
		questionImageLocation = new ImageView();
		questionImageLocation.setFitHeight(200);
		questionImageLocation.setFitWidth(200);
		questionBodyLabel = new Label("Question " + currentQuestionNumber + ": ");
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
					Alert totalScore = new Alert(AlertType.INFORMATION);
					totalScore.setTitle("Total Score");
					totalScore.setContentText("You got " + main.currQuiz.numCorrect + 
							" questions correct out of " + main.currQuiz.numQuestions + " possible ("
									+ (int) main.currQuiz.numCorrect / main.currQuiz.numQuestions*100 + "%)");
					totalScore.showAndWait();
					primaryStage.setScene(main.home);
					previousQuestionCorrect.setVisible(false);
					previousQuestionIncorrect.setVisible(false);
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
		questionBodyLabel.setText("Question: " + currQuestion.getQuestionText()); // Make These to get methods
		questionTopicLabel.setText("Topic: " + currQuestion.getTopic());
		currentQuestionNumber++; // increment the display for the current question upon advancing questions

		if (tg.getSelectedToggle() != null) {
		  tg.getSelectedToggle().setSelected(false);
		}
		
		for (int i = 0; i < choiceIsCorrect.length; i++) {
			choiceText[i].setText(currQuestion.getChoices().get(i).getChoiceText());// Use getter
			choiceIsCorrect[i] = new RadioButton();

			choiceIsCorrect[i].setId(i + ""); // Saves which answer it's associated with
			choiceIsCorrect[i].setToggleGroup(tg);
		}
	}

}