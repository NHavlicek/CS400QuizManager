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
  
  public boolean checkAnswer(AnswerChoice userAnswer) {
	  if (!CHOICES.contains(userAnswer)) { // ensure answer is in arraylist (should be)
		  return false;
	  } else {
		  return CHOICES.get(CHOICES.indexOf(userAnswer)).isCorrectChoice; // return correct choice or not
	  }
  }
  
  public String getQuestionText() {
	  return QUESTION_TEXT;
  }
  
  public ArrayList<AnswerChoice> getChoices() {
	  return CHOICES;
  }
  
  public String getTopic() {
	  return TOPIC;
  }
  
  public BufferedImage getImage() {
	  return IMAGE;
  }

}
