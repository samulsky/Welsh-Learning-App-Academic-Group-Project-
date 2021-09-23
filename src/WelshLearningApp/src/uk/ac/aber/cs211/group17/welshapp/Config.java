/*
 * @(#) Config.java 1.0 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group17.welshapp;

/**
 * Config - A class to that controls the backend.
 * <p>
 * The class is used to run the backend application
 *
 * @author (name)
 * @version 1
 */

public class Config {

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    Dictionary dictionary = new Dictionary();
    MyWords myWords = new MyWords();

    // //////// //
    // Methods. //
    // //////// //
    public void runApp() {
        String dictionaryJson = "Assets/dictionary.json";
        String practiceListJson = "Assets/practiceList.json";
        dictionary.loadDictList(dictionaryJson);
        myWords.loadPracList(practiceListJson);

//
    }
}
