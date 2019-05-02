package application;

/**
 * Filename: AnswerChoice.java Project: A-Team Quiz Application Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * This method is mainly used in the Question class as an element in an array
 * list of possible answers for the user to choose when the question is
 * displayed.
 */
public class AnswerChoice {
	final String choiceText; // text that the user adds as a choice when making questions
	final boolean isCorrectChoice; // if this choice is chosen by user, this decides if
								   // the answer was correct

	/**
	 * Constructor instantiates the fields of the method.
	 * 
	 * @param choiceText      - the text displayed in the answer selection boxes in
	 *                        the quizzing GUI
	 * @param isCorrectChoice - Boolean value of if this AnswerChoice is the correct
	 *                        answer to the Question
	 */
	public AnswerChoice(String choiceText, boolean isCorrectChoice) {
		// updates fields
		this.choiceText = choiceText;
		this.isCorrectChoice = isCorrectChoice;
	}
}
