/*
 * @(#) TranslateWordController.java 1 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * TranslateWordController - A class to control GUI for the game in which the user has
 * to type the translation of a word
 *
 * <p>
 * Used to control the window for the translation game
 *
 * @author jub27
 * @version 1
 * @see MyWords
 * @see PracticeController
 * @see MatchWordsController
 * @see GuessWordController
 */

public class TranslateWordController implements Initializable {
    // //////////////// //
    // Class variables. //
    // //////////////// //
    public static Dictionary dictionary = new Dictionary();
    public static PracticeGames practiceGames = new PracticeGames();

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    public Button nextWordButton;
    public Button answerButton;
    public ProgressBar progressBar;
    public Text wordLabel;
    public TextField userAnswer;

    public Word word;
    public double progressBarCounter;
    public int correct;

    // //////// //
    // Methods. //
    // //////// //
    public void goMenu() throws IOException {
   // UI.backToMenu();
    }

    public void Exit() {
        Platform.exit();
        System.exit(0);
    }

    public void goBack() throws IOException {
        UI.showPractice();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        correct = 0;
        progressBarCounter = 0;
        dictionary.loadDictList("Assets/practiceList.json");
        practiceGames.resetDict(dictionary);
        word = practiceGames.getWord();
        wordLabel.setText(word.getEnglishWord());
        userAnswer.setText("");
    }

    public void pass(){
        word = practiceGames.getWord();
        wordLabel.setText(word.getEnglishWord());
        userAnswer.setText("");
        progressBarCounter += 1;
        updateProgressBar();
    }

    public void mark(){
        if(userAnswer.getText().toLowerCase() == word.getWelshWord().toLowerCase()){
            correct++;
        }
        if(progressBarCounter < 20){
            pass();
        }else{
            wordLabel.setText("you got " + correct + " questions right");
        }
    }

    public void updateProgressBar(){
        progressBar.setProgress(progressBarCounter/20);
    }
}
