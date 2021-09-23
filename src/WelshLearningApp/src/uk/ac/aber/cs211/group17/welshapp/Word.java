/*
 * @(#) Word.java 1.0 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs211.group17.welshapp;
import org.json.simple.JSONObject;
import java.util.Iterator;
import java.util.Objects;

/**
 * Word - A class to represent a word in the dictionary.
 * <p>
 * The class is used to represent a word in the dictionary
 * and in my words. Its variable are English and Welsh translation
 * and its type e.g "verb"
 *
 * @author (name)
 * @version 1
 * @see Dictionary
 * @see MyWords
 */
public class Word {


    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    private String englishWord;
    private String welshWord;
    private String wordType;

    // ///////////// //
    // Constructors. //
    // ///////////// //
    public Word(String english, String welsh, String type) {
        englishWord = english;
        welshWord = welsh;
        wordType = type;

    }

    public Word() {
    }

    // ////////////////////// //
    // Read/Write properties. //
    // ////////////////////// //

    /**
     * Method to load a word form file
     *
     * @param iterator an iterator to iterate over JSONObjects
     */
    public void load(Iterator<JSONObject> iterator) {

        JSONObject object = iterator.next();
        englishWord = (String) object.get("english");
        welshWord = (String) object.get("welsh");
        wordType = (String) object.get("wordType");

    }

    /**
     * A method used to save words to a file
     *
     * @param object word to save
     * @param welsh welsh word
     * @param wordType word type
     * @param english english word
     */
    public void save(JSONObject object, String welsh, String wordType, String english) {

        object.put("welsh", welsh);
        object.put("wordType", wordType);
        object.put("english", english);
    }

    // //////// //
    // Methods. //
    // //////// //

    /**
     * This method compares two words together by looking at
     * their English and Welsh translation and its type
     *
     * @param o a word to compare
     * @return boolean True if the are the same word or false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(englishWord, word.englishWord) &&
                Objects.equals(welshWord, word.welshWord) &&
                Objects.equals(wordType, word.wordType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(englishWord, welshWord, wordType);
    }

    /**
     * Method to access the English translation
     * @return String (English word)
     */
    public String getEnglishWord() {
        return englishWord;
    }
    /**
     * Method to access the Welsh translation
     * @return String (Welsh word)
     */
    public String getWelshWord() {
        return welshWord;
    }

    /**
     * Method to access the word type
     * @return String (word type)
     */
    public String getWordType() {
        return wordType;
    }


}
