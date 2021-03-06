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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Filename: SelectionGUI.java Project: A-Team Quiz Application Team: A-Team 27 Authors: Nicholas
 * Havlicek, Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * stores all the questions that have been added by the user
 */
public class QuestionBank {
  private int totalNumQuestions;
  private ArrayList<Question> questionBank;

  public QuestionBank() {
    totalNumQuestions = 0;
    questionBank = new ArrayList<Question>();
  }

  /*
   * Load all the questions to the database
   */
  public void loadQuestions(String filepath, Main main) throws IOException, ParseException {
    File file = new File(filepath);
    FileReader readFile = new FileReader(file);
    Object tempObj = new JSONParser().parse(readFile);
    JSONObject obj = (JSONObject) tempObj;
    JSONArray questions = (JSONArray) obj.get("questionArray");

    //Loops through the questionArray creating new questions
    for (int i = 0; i < questions.size(); i++) {
      JSONObject jsonQuestion = (JSONObject) questions.get(i);
      String questionText = (String) jsonQuestion.get("questionText");
      String questionTopic = (String) jsonQuestion.get("topic");
      String imageName = (String) jsonQuestion.get("image");
      BufferedImage image = null;
      if (!imageName.equals("none")) {
        File inputFile = new File(imageName); // image file path
        int width = 963; // width of the image
        int height = 640; // height of the image
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image = ImageIO.read(inputFile);
      }
      
      //Loops through choices adding them to question object
      JSONArray questionChoices = (JSONArray) jsonQuestion.get("choiceArray");
      List<AnswerChoice> choices = new ArrayList<AnswerChoice>();
      for (int j = 0; j < questionChoices.size(); j++) {
        String currChoice = questionChoices.get(j).toString();
        String[] temp = currChoice.split("\",\"");
        String[] isCorrect = temp[1].split(":");
        String[] choice = temp[0].split(":");
        isCorrect[1] = isCorrect[1].replace("\"", "");
        choice[1] = choice[1].replace("\"", "");
        boolean correctness = false;
        if (isCorrect[1].equals("T}"))
          correctness = true;

        choices.add(new AnswerChoice(choice[1], correctness));
      }
      Question newQuestion = new Question(questionText, questionTopic, choices, image);
      questionBank.add(newQuestion);
      if (!main.totalTopicsList.contains(questionTopic))
        main.totalTopicsList.add(questionTopic);
      totalNumQuestions++;
    }
  }

  /**
   * add a question to the question bank
   * 
   * @param newQuestion the question to be added
   */
  public void addQuestion(Question newQuestion) {
    // update the topics list in main
    if (newQuestion != null) {
      questionBank.add(newQuestion);
    }
    totalNumQuestions++;
  }

  /**
   * helper method to generate a quiz from the question bank
   * 
   * @param topic the topic that we want to find
   * @return an arraylist of the matching questions
   */
  public ArrayList<Question> findAllQuestionsWithTopic(String topic) {
    ArrayList<Question> ret = new ArrayList<Question>();
    for (Question question : questionBank) {
      if (question.getTopic().equals(topic)) {
        ret.add(question);
      }
    }
    return ret;
  }

  /**
   * randomly generate a quiz from the entire bank of questions
   * 
   * @param selectedTopics the topics that this quiz will be comprised of
   * @param numQuestions   the number of questions in this quiz
   * @return a Quiz with numQuestions number of questions and topics that match selectedTopics
   */
  public Quiz generateQuiz(HashSet<String> selectedTopics, int numQuestions) {
    String[] topicslist = selectedTopics.toArray(new String[0]);
    ArrayList<Question> matchingQuestions = new ArrayList<Question>();
    for (int i = 0; i < topicslist.length; ++i) {
      matchingQuestions.addAll(findAllQuestionsWithTopic(topicslist[i]));
    }

    if (matchingQuestions.size() < numQuestions) {
      return null; // Invalid number of questions
    }

    Collections.shuffle(matchingQuestions); // randomize
    Quiz ret = new Quiz(matchingQuestions.subList(0, numQuestions)); // construct quiz
    return ret;
  }

  public ArrayList<Question> getQuestionBank() {
    return questionBank;
  }

  public int getTotalNumQuestions() {
    return totalNumQuestions;
  }

  /**
   * given a file path, save all the questions in this question bank to a json file at the specified
   * location
   * 
   * @param filepath string representation of the file path to be used
   */
  @SuppressWarnings("unchecked")
  public void saveQuestionsToFile(String filepath) throws IOException {
    JSONObject obj = new JSONObject();
    JSONArray allQuestions = new JSONArray();
    JSONArray choices;
    JSONObject option;

    // Loops through all the questions stored and adds them to to JSON file
    for (Question quest : questionBank) {

      // Creates JSON object
      JSONObject question = new JSONObject();

      choices = new JSONArray();

      // Loops through the choices of a question and adds them
      for (int i = 0; i < quest.getChoices().size(); i++) {
        option = new JSONObject();
        if (quest.getChoices().get(i) != null) {
          if (quest.getChoices().get(i).getIsCorrect()) {
            option.put("isCorrect", "T");
            option.put("choice", quest.getChoices().get(i).getChoiceText());
          } else {
            option.put("isCorrect", "F");
            option.put("choice", quest.getChoices().get(i).getChoiceText());
          }
        }
        choices.add(option);
      }

      question.put("meta-data", "unused");
      question.put("questionText", quest.getQuestionText());
      question.put("topic", quest.getTopic());
      if (quest.getImage() != null) {
        question.put("image", quest.getImage());
      } else {
        question.put("image", "none");
      }
      allQuestions.add(question);
      question.put("choiceArray", choices);
    }
    obj.put("questionArray", allQuestions);

    // Saves file to specified file path
    try (
        FileWriter file = new FileWriter(filepath)
    ) {

      file.write(obj.toJSONString());
      file.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}