/*
 * @(#) Dictionary.java 1.0 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Dictionary - A class to represent a dictionary which stores words.
 * <p>
 * The class is used to represent a dictionary with words
 *
 * @author (name)
 * @version 1
 * @see Word
 * @see MyWords
 */

public class Dictionary {


    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    private Scanner scan = new Scanner(System.in);
    protected SortedMap<String, Word> dictEnglishMap = new TreeMap<>();


    // ///////////// //
    // Constructors. //
    // ///////////// //
    public Dictionary() {
    }


    // ////////////////////// //
    // Read/Write properties. //
    // ////////////////////// //

    //load file
    protected void loadDictList(String fileName) {
        loadList(fileName, dictEnglishMap);

    }

    //load dictionary from a json file
    protected void loadList(String fileName, SortedMap<String, Word> englishMap) {
        JSONParser parser = new JSONParser();
        try {
            Object fileObj = parser.parse(new FileReader(fileName));
            JSONArray obj = (JSONArray) fileObj;
            Iterator<JSONObject> iterator = obj.iterator();
            //goes through all the objects

            while (iterator.hasNext()) {
                Word loadWord = null;
                loadWord = new Word();
                loadWord.load(iterator);
                englishMap.put(loadWord.getEnglishWord(), loadWord);
                //welshMap.put(loadWord.getWelshWord(), loadWord);
            }
            System.out.println("Words loaded");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //save the dictionary to a json file
    void saveList(String fileName, ObservableList<Word> dictWords) {
        JSONObject object = new JSONObject();
        // creates a string that will be used to write the json file
        String output = "[";
        // puts all the objects into the string
        for (int counter = 0; counter < dictWords.size(); counter++) {
            object.put("welsh", dictWords.get(counter).getWelshWord());
            object.put("wordType", dictWords.get(counter).getWordType());
            object.put("english", dictWords.get(counter).getEnglishWord());
            // checks if there is another word after the current word and if it needs a ',' at the end of the object in the string
            if (counter != dictWords.size() - 1) {
                output = output + object.toString() + ",";
            } else {
                output = output + object.toString();
            }
        }
        output = output + "]";
        // writes the string to the new file
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(output);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // //////// //
    // Methods. //
    // //////// //

    //display words stored in the map
    protected ArrayList<Word> displayWords(SortedMap<String, Word> map) {
        ArrayList<Word> wordList = new ArrayList<>();

        Set s = map.entrySet();

        // Using iterator in SortedMap
        Iterator i = s.iterator();

        // Traversing map. Note that the traversal
        // produced sorted (by keys) output .
        while (i.hasNext()) {
            Map.Entry m = (Map.Entry) i.next();
            wordList.add((Word) m.getValue());
            ;

        }
//    System.out.println(saveList.size());
        return wordList;
    }

    //access the dictionary map
    public SortedMap<String, Word> getDictEnglishMap() {
        return dictEnglishMap;
    }
}

