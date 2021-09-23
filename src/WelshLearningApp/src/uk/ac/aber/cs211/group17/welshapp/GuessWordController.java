/*
 * @(#) GuessWordController.java 1 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

/**
 * GuessWordController - A class to control the guess word window.
 * <p>
 * Used to control the window in which the practice game guess the word is displayed
 *
 * @author jub27
 * @version 1
 * @see MyWords
 * @see PracticeController
 * @see MatchWordsController
 * @see TranslateWordController
 */

public class GuessWordController implements Initializable {

    // //////////////// //
    // Class variables. //
    // //////////////// //
    public static Dictionary dictionary = new Dictionary();
    public static PracticeGames practiceGames = new PracticeGames();

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    public ArrayList<Word> words = new ArrayList<>();
    public Question question = new Question();
    boolean correct = true;
    public Button word1;
    public Button word2;
    public Button word3;
    public Button word4;
    public Button word5;
    public Button word6;
    public Text wordLabel;


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
        newQuestion();
    }

    public void newQuestion(){

        word1.setTextFill(BLACK);
        word2.setTextFill(BLACK);
        word3.setTextFill(BLACK);
        word4.setTextFill(BLACK);
        word5.setTextFill(BLACK);
        word6.setTextFill(BLACK);
        word1.setFont(Font.font("System", FontWeight.NORMAL,12));
        word2.setFont(Font.font("System", FontWeight.NORMAL,12));
        word3.setFont(Font.font("System", FontWeight.NORMAL,12));
        word4.setFont(Font.font("System", FontWeight.NORMAL,12));
        word5.setFont(Font.font("System", FontWeight.NORMAL,12));
        word6.setFont(Font.font("System", FontWeight.NORMAL,12));
        words = practiceGames.GetWords(6);
        String correctEnglishAnswer = words.get(0).getEnglishWord();
        String correctWelshAnswer = words.get(0).getWelshWord();
        words = practiceGames.jumbledWordsShuffleWords(words,6);
        question.setAnswer(correctWelshAnswer);
        question.setOption1(words.get(0).getWelshWord());
        question.setOption2(words.get(1).getWelshWord());
        question.setOption3(words.get(2).getWelshWord());
        question.setOption4(words.get(3).getWelshWord());
        question.setOption5(words.get(4).getWelshWord());
        question.setOption6(words.get(5).getWelshWord());
        question.setWordToAnswer(correctEnglishAnswer);
        word1.setText(question.getOption1());
        word2.setText(question.getOption2());
        word3.setText(question.getOption3());
        word4.setText(question.getOption4());
        word5.setText(question.getOption5());
        word6.setText(question.getOption6());
        wordLabel.setText(question.getWordToAnswer());
        correct = true;
    }

    public void mark(String answer){
        if(answer == question.getAnswer()){
            newQuestion();
        }else{
            correctAnswer();
        }
    }

    public void correctAnswer(){
        if(question.getAnswer() == question.getOption1()){
            word1.setTextFill(GREEN);
            word1.setFont(Font.font("System", FontWeight.BOLD,12));
        }else if(question.getAnswer() == question.getOption2()){
            word2.setTextFill(GREEN);
            word2.setFont(Font.font("System", FontWeight.BOLD,12));
        }else if(question.getAnswer() == question.getOption3()){
            word3.setTextFill(GREEN);
            word3.setFont(Font.font("System", FontWeight.BOLD,12));
        }else if(question.getAnswer() == question.getOption4()){
            word4.setTextFill(GREEN);
            word4.setFont(Font.font("System", FontWeight.BOLD,12));
        }else if(question.getAnswer() == question.getOption5()){
            word5.setTextFill(GREEN);
            word5.setFont(Font.font("System", FontWeight.BOLD,12));
        }else if(question.getAnswer() == question.getOption6()){
            word6.setTextFill(GREEN);
            word6.setFont(Font.font("System", FontWeight.BOLD,12));
        }
        correct = false;
    }

    public void button1Pressed(){
        if(correct == true){
            mark(word1.getText());
        }else{
            newQuestion();
        }
    }

    public void button2Pressed(){
        if(correct == true){
            mark(word2.getText());
        }else{
            newQuestion();
        }
    }

    public void button3Pressed(){
        if(correct == true){
            mark(word3.getText());
        }else{
            newQuestion();
        }
    }

    public void button4Pressed(){
        if(correct == true){
            mark(word4.getText());
        }else{
            newQuestion();
        }
    }

    public void button5Pressed(){
        if(correct == true){
            mark(word5.getText());
        }else{
            newQuestion();
        }
    }

    public void button6Pressed(){
        if(correct == true){
            mark(word6.getText());
        }else{
            newQuestion();
        }
    }
}
