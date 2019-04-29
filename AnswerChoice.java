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
  final String choiceText;
  final boolean isCorrectChoice;

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
}