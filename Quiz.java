//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Final Project: QuizManager
//
// Files: Main.java, AddQuestion.java, AnswerChoice.java, 
//    ConfimationUponExitGUI.java, HomeScreen.java, LoadGUI.java, Question.java
//    QuestionBank.java, Quiz.java, QuizzingGUI, SaveGUI.java, SelectionGUI.java
//    application.css
//
// Course: CS 400 Spring 2019
//
// Author: Dung Vo, Murad Jaber, Nick Havlicek, Kevin Kim, Spencer Runde
// Email: dvo2@wisc.edu, mjaber2@wisc.edu, nhavlicek@wisc.edu, runde3@wisc.edu
//        kkim434@wisc.edu
// Lecturer's Name: Andrew Kuemmel, Debra Deppler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: N/A
// Online Sources: N/A
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

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
		questions.addAll(list);
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
		return currQuestion == (numQuestions - 1);
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
	
	//Returns list of questions
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	
	//Returns the current question user is on
	public Question getCurrQuestion() {
		return questions.get(currQuestion);
	}
}
