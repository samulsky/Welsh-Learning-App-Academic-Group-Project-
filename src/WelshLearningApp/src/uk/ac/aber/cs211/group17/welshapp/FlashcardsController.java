/*
 * @(#) FlashcardsController.java 1 2020/04/30
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


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FlashcardsController - A class to control the Flashcards window.
 * <p>
 * Used to control the window in which the flashcard are displayed
 *
 * @author jub27
 * @version 1
 */


public class FlashcardsController implements Initializable {
    // //////////////// //
    // Class variables. //
    // //////////////// //
    public static Dictionary dictionary = new Dictionary();
    public static PracticeGames practiceGames = new PracticeGames();

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    public Word word;
    public double progressBarCounter;
    public Button nextWord;
    public Button flashCard;
    public ProgressBar progressBar = new ProgressBar(0);

    // //////// //
    // Methods. //
    // //////// //
    public void goMenu() throws IOException {
        //UI.backToMenu();
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
        dictionary.loadDictList("Assets/practiceList.json");
        practiceGames.resetDict(dictionary);
        progressBarCounter = 0;
        newFlashCard();
    }

    public void newFlashCard(){
        word = practiceGames.getWord();
        flashCard.setText(word.getEnglishWord());
    }

    public void clickFlashCard(){
        if(flashCard.getText() == word.getEnglishWord()){
            flashCard.setText(word.getWelshWord());
        }else{
            flashCard.setText(word.getEnglishWord());
        }
    }

    public void setNextWord() throws IOException{
        if(progressBarCounter != 20){
            progressBarCounter += 1;
            newFlashCard();
            updateProgressBar();
        }else{
            UI.showPractice();
        }
    }

    public void updateProgressBar(){
        progressBar.setProgress(progressBarCounter/20);
    }
}
