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

// import application;

import java.util.HashSet;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

/**
 * Filename: Main.java Project: A-Team Quiz Application Team: A-Team 27 Authors: Nicholas Havlicek,
 * Murad Jaber, Kevin Kim, Spencer Runde, Dung Vo
 * 
 * Main GUI which sets the scenes shown to the user
 */
public class Main extends Application {

  /**
   * scenes explained: 1. home : the first screen seen upon opening the application. Save, load
   * files, add questions, enter the actual application. Can also exit.
   * 
   * 2. quizHome : The scene entered when the user hits "select quiz". A quiz customization screen.
   * User can select topics and see what is displayed. Enters the quiz upon hitting "quiz me"
   * 
   * 3. startQuizzing : the actual quizzing part. Shows the current question, multiple answer
   * options, a button to advance to the next question (hidden before answer given?), an exit
   * button, and when answered an indication of correctness. Optional extras: question number info
   * (number correct, number left, etc.)
   * 
   * 4. confirmationExit : checks if the user wants to save before exiting.
   * 
   * 5. addQuestionsScreen: has text input fields for manually-submitted questions. Displays the
   * topics currently in the question bank
   * 
   * TODO more to come
   */
  public Scene home, quizHome, startQuizzing, confirmationExit, addQuestionScreen, quizzingScreen,
      saveScreen, loadScreen;
  public QuestionBank allQuestions;
  public Insets buttonSpacing = new Insets(15, 12, 15, 12); // spacing for buttons (standard)
  public HashSet<String> totalTopicsList = new HashSet<String>(); // all topics among all questions
  public HashSet<String> selectedTopicsList = new HashSet<String>(); // only topics selected by
                                                                     // user
  public Quiz currQuiz;
  private HomeScreenGUI homeScreen;
  private SelectionGUI quizEntry;
  private ConfirmationUponExitGUI confirmExit;
  public QuizzingGUI quizMeGUI;
  private AddQuestionGUI addQ;
  private SaveGUI saveQuestionsGUI;
  private LoadGUI loadQuestionsGUI;

  /**
   * Creates the main GUI and controls the GUIs
   * 
   * @param primaryStage The main stage shown
   */
  @Override
  public void start(Stage primaryStage) {
    allQuestions = new QuestionBank(); // initialize the questionBank

    // home screen
    homeScreen = new HomeScreenGUI(this, primaryStage);
    home = new Scene(homeScreen, 600, 600);

    // main quiz screen
    quizEntry = new SelectionGUI(this, primaryStage);
    quizHome = new Scene(quizEntry, 600, 600);

    // confirmation upon exit
    confirmExit = new ConfirmationUponExitGUI(this, primaryStage);
    confirmationExit = new Scene(confirmExit, 600, 600);

    // GUI during quizzing TODO implement
    quizMeGUI = new QuizzingGUI(this, primaryStage);
    quizzingScreen = new Scene(quizMeGUI, 600, 600);

    // add question screen
    addQ = new AddQuestionGUI(this, primaryStage);
    addQuestionScreen = new Scene(addQ, 600, 600);

    // save questions screen
    saveQuestionsGUI = new SaveGUI(this, primaryStage);
    saveScreen = new Scene(saveQuestionsGUI, 600, 600);

    // load questions screen
    loadQuestionsGUI = new LoadGUI(this, primaryStage);
    loadScreen = new Scene(loadQuestionsGUI, 600, 600);

    // display the home screen first
    primaryStage.setScene(home);
    primaryStage.show();
  }

  /**
   * Launches the application
   * 
   * @param args Arguments passed to the launch
   */
  public static void main(String[] args) {
    System.out.println("before launch");
    launch(args);
    System.out.println("after launch");
  }

  public void updateAll() {
    homeScreen.updateValue(this);
    quizEntry.updateValue(this);
  }

}
