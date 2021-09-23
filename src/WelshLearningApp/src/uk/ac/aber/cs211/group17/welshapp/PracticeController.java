/*
 * @(#) PracticeController.java 1 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.application.Platform;

import java.io.IOException;

/**
 * PracticeController - A class to control GUI for practice
 * <p>
 * Used to control the practise games the user can run
 *
 * @author (name)
 * @version 1
 * @see MyWords
 * @see GuessWordController
 * @see MatchWordsController
 * @see TranslateWordController
 */

public class PracticeController {

    // //////// //
    // Methods. //
    // //////// //
    public void goFlashcards() throws Exception{
        UI.showFlashCards();
    }

    public void goGuessWord() throws Exception{
        UI.showGuessWord();
    }

    public void goTranslateWord() throws Exception{
        UI.showTranslateWord();
    }

    public void goMatchWord() throws Exception{
        UI.showMatchWord();
    }

    public void goDictionary() throws IOException {
        UI.backToDictionary();
    }

    public void Exit() {

        Platform.exit();
        System.exit(0);
    }

    public void goBack() throws IOException {
        UI.showMyWords();
    }
}
