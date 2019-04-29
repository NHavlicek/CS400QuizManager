package application;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
	int numCorrect; // the number of correct answers
	int numQuestions; // the number of questions in this quiz
	int currQuestion;
	ArrayList<Question> questions;
	
	public Quiz(List<Question> list) {
		currQuestion = 0;
		numQuestions = list.size();
		questions = new ArrayList<Question>();
		// add the questions in the List<Question> object to the data structure
		// set currQuestion to first question
	}
	
	/**
	 * iterates successively down the ArrayList of questions
	 * @return the next question in the list
	 */
	public Question getNextQuestion() {
		if (currQuestion >= numQuestions) {
			return null;
		}
		currQuestion++;
		return questions.get(currQuestion);
	}
	
	/**
	 * return true if we've reached the end of the quiz
	 * @return
	 */
	public boolean quizOver() {
		return currQuestion == numQuestions;
	}
	
	/**
	 * check the correctness of the user answer on the current question
	 * @param userAnswer
	 * @see Question.java checkAnswer();
	 * @return
	 */
	public boolean checkAnswer(AnswerChoice userAnswer) {
		return (questions.get(currQuestion).checkAnswer(userAnswer));
	}
}
