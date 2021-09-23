/*
 * @(#) Question.java 1.0 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;
import java.util.ArrayList;

/**
 * Question - A class to represent a practice list where the user can save word.
 * <p>
 * The class is used to store words that the user saves to their practice list
 *
 * @author (name)
 * @version 1
 * @see Word
 * @see MyWords
 * @see Dictionary
 */

public class Question {

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    private String wordToAnswer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
    private String answer;

    // //////// //
    // Methods. //
    // //////// //
    public Question() {
    }

    public Question(String wordToAnswer, String option1, String option2, String option3, String option4, String option5,String option6, String answer) {
        this.wordToAnswer = wordToAnswer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.answer = answer;
    }

    String getWordToAnswer() {
        return wordToAnswer;
    }

    public void setWordToAnswer(String wordToAnswer) {
        this.wordToAnswer = wordToAnswer;
    }

    String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getOption6() {
        return option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }
}
