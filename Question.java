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

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * Filename: Question.java
 * Project: A-Team Quiz Application
 * Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * Class to maintain information about individual questions in the quiz.
 */
public class Question {
	  private final String QUESTION_TEXT; // body of the question
	  private final ArrayList<AnswerChoice> CHOICES;
	  private final String TOPIC;
	  private final BufferedImage IMAGE;

  /**
   * Creates a question given the question, topic, and answers
   * 
   * @param questionText    the body of the question
   * @param topic           the topic of the question
   * @param possibleChoices an arraylist of possible answers
   */
  public Question(String questionText, String topic, List<AnswerChoice> possibleChoices,
	      BufferedImage image) {
	    this.QUESTION_TEXT = questionText;
	    this.TOPIC = topic;
	    CHOICES = new ArrayList<AnswerChoice>();
	    for (int i = 0; i < possibleChoices.size(); ++i) {
	      CHOICES.add(possibleChoices.get(i));
	    }
	    this.IMAGE = image;
  }
  
  //Checks if answer is correct answer from options
  public boolean checkAnswer(AnswerChoice userAnswer) {
	  if (!CHOICES.contains(userAnswer)) { // ensure answer is in arraylist (should be)
		  return false;
	  } else {
		  return CHOICES.get(CHOICES.indexOf(userAnswer)).getIsCorrect(); // return correct choice or not
	  }
  }
  
  //Returns the question
  public String getQuestionText() {
	  return QUESTION_TEXT;
  }
  
  //Returns the question options
  public ArrayList<AnswerChoice> getChoices() {
	  return CHOICES;
  }
  
  //Returns the topic of the question
  public String getTopic() {
	  return TOPIC;
  }
  
  //Returns the image of the question
  public BufferedImage getImage() {
	  return IMAGE;
  }

}