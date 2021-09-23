/*
 * @(#) PracticeGames.java 1.0 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import java.util.*;

/**
 * PracticeGames - A class to represent a practice games that can be played by the user.
 * <p>
 * The class is used to play practice games with words form the practice list
 *
 * @author (name)
 * @version 1
 */

public class PracticeGames {

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
//    private Scanner scan = new Scanner(System.in);
    Dictionary dictionary = new Dictionary();
    SortedMap<String, Word> engDict = dictionary.getDictEnglishMap();
    //SortedMap<String, Word> welDict = dictionary.getDictWelshMap();


    // //////// //
    // Methods. //
    // //////// //
    public void resetDict(Dictionary d) {
        dictionary = d;
        engDict = dictionary.getDictEnglishMap();
        //welDict = dictionary.getDictWelshMap();
    }



    protected Word getWord() {
        Object[] num = dictionary.dictEnglishMap.keySet().toArray();
        Object key = num[new Random().nextInt(num.length)];
        return dictionary.getDictEnglishMap().get(key);
    }


    // jumbled words

    // storing marks
    int correct = 0;

    protected ArrayList<Word> GetWords(int amount) {

        // create arraylist
        ArrayList<Word> words = new ArrayList<>();

        // gets random words from dictionary
        Object[] num = dictionary.dictEnglishMap.keySet().toArray();
        Object key = num[new Random().nextInt(num.length)];

        // loop for amount of words wanted
        for(int i = 0; i <= amount; i++){
            key = num[new Random().nextInt(num.length)];
            words.add(dictionary.getDictEnglishMap().get(key));
        }
        return words;
    }

    //shuffle words into a random order
    protected ArrayList<Word> jumbledWordsShuffleWords(ArrayList<Word> words, int amount){
        // creates a list to store the welsh value of words
        List<Word> shuffledWords = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            shuffledWords.add(words.get(i));
        }

        // shuffles words for a random order
        Collections.shuffle(shuffledWords);

        // puts words into an array
        ArrayList<Word> shuffledArray = new ArrayList<>(shuffledWords);
        return shuffledArray;
    }

    protected String jumbledWordsMark(ArrayList<Word> words, Word word1,Word word2, Word word3, Word word4) {
        correct = 0;
        if(words.get(0) == word1){
            correct++;
        }
        if(words.get(1) == word2){
            correct++;
        }
        if(words.get(2) == word3){
            correct++;
        }
        if(words.get(3) == word4){
            correct++;
        }
        return "You got " + correct + " answers correct";
    }

}
