/*
 * @(#) MyWords.java 1.0 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * MyWords - A class to represent a practice list where the user can save word.
 * <p>
 * The class is used to store words that the user saves to their practice list
 *
 * @author (name)
 * @version 1
 * @see Word
 * @see MyWords
 * @see Dictionary
 */
public class MyWords extends Dictionary{

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    SortedMap<String, Word> pracEnglishMap = new TreeMap<>();


    // //////// //
    // Methods. //
    // //////// //
    protected void loadPracList(String fileName){ loadList(fileName, pracEnglishMap);
    }

}
