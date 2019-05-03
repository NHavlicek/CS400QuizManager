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

/**
 * Filename: AnswerChoice.java
 * Project: A-Team Quiz Application
 * Team: A-Team 27
 * Authors: Nicholas Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * Is a choice for a question
 */
public class AnswerChoice {
  private final String choiceText;
  private final boolean isCorrectChoice;

  /**
   * Sets up a choice for the question
   * 
   * @param choiceText      The text of the choice
   * @param isCorrectChoice If the choice is correct or not
   */
  public AnswerChoice(String choiceText, boolean isCorrectChoice) {
    this.choiceText = choiceText;
    this.isCorrectChoice = isCorrectChoice;
  }
  
  //Returns true if choice is correct answer
  public boolean getIsCorrect() {
	  return isCorrectChoice;
  }
  
  //Returns the choice text
  public String getChoiceText() {
	  return choiceText;
  }
}