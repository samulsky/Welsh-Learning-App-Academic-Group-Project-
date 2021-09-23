/*
 * @(#) MatchWordsController.java 1 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.scene.paint.Color.*;

/**
 * MatchWordsController - A class to control the matching words window.
 * <p>
 * Used to control the window in which the practice game match the words is displayed
 *
 * @author jub27
 * @version 1
 * @see MyWords
 * @see PracticeController
 * @see GuessWordController
 * @see TranslateWordController
 */

public class MatchWordsController implements Initializable {
    // //////////////// //
    // Class variables. //
    // //////////////// //
    public static Dictionary dictionary = new Dictionary();
    public static PracticeGames practiceGames = new PracticeGames();

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    public Button englishButton1;
    public Button englishButton2;
    public Button englishButton3;
    public Button englishButton4;
    public Button welshButton1;
    public Button welshButton2;
    public Button welshButton3;
    public Button welshButton4;
    public Text marksLabel;

    public ArrayList<Word> words = new ArrayList<>();
    public ArrayList<Word> shuffledWords = new ArrayList<>();

    public Word button1Match = null;
    public Word button2Match = null;
    public Word button3Match = null;
    public Word button4Match = null;


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
        words = practiceGames.GetWords(4);
        shuffledWords = practiceGames.jumbledWordsShuffleWords(words,4);
        englishButton1.setText(words.get(0).getEnglishWord());
        englishButton2.setText(words.get(1).getEnglishWord());
        englishButton3.setText(words.get(2).getEnglishWord());
        englishButton4.setText(words.get(3).getEnglishWord());
        welshButton1.setText(shuffledWords.get(0).getWelshWord());
        welshButton2.setText(shuffledWords.get(1).getWelshWord());
        welshButton3.setText(shuffledWords.get(2).getWelshWord());
        welshButton4.setText(shuffledWords.get(3).getWelshWord());
    }

    public int buttonClicked = 0;
    public void clickEnglishButton1(){
        buttonClicked = 1;
        englishButton1.setTextFill(BLUE);
    }

    public void clickEnglishButton2(){
        buttonClicked = 2;
        englishButton2.setTextFill(RED);
    }

    public void clickEnglishButton3(){
        buttonClicked = 3;
        englishButton3.setTextFill(GREEN);
    }

    public void clickEnglishButton4(){
        buttonClicked = 4;
        englishButton4.setTextFill(ORANGE);
    }

    public void clickWelshButton1(){
        if(buttonClicked == 1){
            welshButton1.setTextFill(BLUE);
            button1Match = shuffledWords.get(0);
        }else if(buttonClicked == 2){
            welshButton1.setTextFill(RED);
            button2Match = shuffledWords.get(0);
        }else if(buttonClicked == 3){
            welshButton1.setTextFill(GREEN);
            button3Match = shuffledWords.get(0);
        }else if(buttonClicked == 4){
            welshButton1.setTextFill(ORANGE);
            button4Match = shuffledWords.get(0);
        }
        buttonClicked = 0;

    }

    public void clickWelshButton2(){
        if(buttonClicked == 1){
            welshButton2.setTextFill(BLUE);
            button1Match = shuffledWords.get(1);
        }else if(buttonClicked == 2){
            welshButton2.setTextFill(RED);
            button2Match = shuffledWords.get(1);
        }else if(buttonClicked == 3){
            welshButton2.setTextFill(GREEN);
            button3Match = shuffledWords.get(1);
        }else if(buttonClicked == 4){
            welshButton2.setTextFill(ORANGE);
            button4Match = shuffledWords.get(1);
        }
        buttonClicked = 0;
    }

    public void clickWelshButton3(){
        if(buttonClicked == 1){
            welshButton3.setTextFill(BLUE);
            button1Match = shuffledWords.get(2);
        }else if(buttonClicked == 2){
            welshButton3.setTextFill(RED);
            button2Match = shuffledWords.get(2);
        }else if(buttonClicked == 3){
            welshButton3.setTextFill(GREEN);
            button3Match = shuffledWords.get(2);
        }else if(buttonClicked == 4){
            welshButton3.setTextFill(ORANGE);
            button4Match = shuffledWords.get(2);
        }
        buttonClicked = 0;
    }

    public void clickWelshButton4(){
        if(buttonClicked == 1){
            welshButton4.setTextFill(BLUE);
            button1Match = shuffledWords.get(3);
        }else if(buttonClicked == 2){
            welshButton4.setTextFill(RED);
            button2Match = shuffledWords.get(3);
        }else if(buttonClicked == 3){
            welshButton4.setTextFill(GREEN);
            button3Match = shuffledWords.get(3);
        }else if(buttonClicked == 4){
            welshButton4.setTextFill(ORANGE);
            button4Match = shuffledWords.get(3);
        }
        buttonClicked = 0;
    }

    public void mark(){
        String correct;
        correct = practiceGames.jumbledWordsMark(words,button1Match,button2Match,button3Match,button4Match);
        marksLabel.setText(correct);
        if(words.get(0) == shuffledWords.get(0)){
            welshButton1.setTextFill(BLUE);
        }else if(words.get(0) == shuffledWords.get(1)){
            welshButton2.setTextFill(BLUE);
        }else if(words.get(0) == shuffledWords.get(2)){
            welshButton3.setTextFill(BLUE);
        }else if(words.get(0) == shuffledWords.get(3)){
            welshButton4.setTextFill(BLUE);
        }

        if(words.get(1) == shuffledWords.get(0)){
            welshButton1.setTextFill(RED);
        }else if(words.get(1) == shuffledWords.get(1)){
            welshButton2.setTextFill(RED);
        }else if(words.get(1) == shuffledWords.get(2)){
            welshButton3.setTextFill(RED);
        }else if(words.get(1) == shuffledWords.get(3)){
            welshButton4.setTextFill(RED);
        }

        if(words.get(2) == shuffledWords.get(0)){
            welshButton1.setTextFill(GREEN);
        }else if(words.get(2) == shuffledWords.get(1)){
            welshButton2.setTextFill(GREEN);
        }else if(words.get(2) == shuffledWords.get(2)){
            welshButton3.setTextFill(GREEN);
        }else if(words.get(2) == shuffledWords.get(3)){
            welshButton4.setTextFill(GREEN);
        }

        if(words.get(3) == shuffledWords.get(0)){
            welshButton1.setTextFill(ORANGE);
        }else if(words.get(3) == shuffledWords.get(1)){
            welshButton2.setTextFill(ORANGE);
        }else if(words.get(3) == shuffledWords.get(2)){
            welshButton3.setTextFill(ORANGE);
        }else if(words.get(3) == shuffledWords.get(3)){
            welshButton4.setTextFill(ORANGE);
        }
    }
}
