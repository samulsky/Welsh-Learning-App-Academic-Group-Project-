/*
 * @(#) UI.java 1.0 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * UI - A class to control the graphical user interface
 * <p>
 * The class is used to control the GUI, it displays scenes and changes them
 *
 * @author (name)
 * @version 1
 */

public class UI extends Application {


    // //////////////// //
    // Class variables. //
    // //////////////// //
    private static BorderPane mainLayout;
    public static Config configuration = new Config();


    // ////////////// //
    // Class methods. //
    // ////////////// //
    public static void backToDictionary() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Dictionary.fxml"));
        BorderPane sc = loader.load();
        mainLayout.setCenter(sc);
    }

    public static void showPractice() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UI.class.getResource("Practice.fxml"));
        BorderPane sc = loader.load();
        mainLayout.setCenter(sc);
    }

    public static void showMyWords() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UI.class.getResource("MyWords.fxml"));
        BorderPane sc = loader.load();
        mainLayout.setCenter(sc);
    }


    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    private Stage primaryStage;
    public  ArrayList<Word> dictionaryWords = new ArrayList<>();
    public  ArrayList<Word> pracWords = new ArrayList<>();

    // //////// //
    // Methods. //
    // //////// //
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Welsh Learning App");
//        this.primaryStage.setResizable(false);
        configuration.runApp();
        resetDict(configuration.dictionary);
        resetPracList(configuration.myWords);
        loadWordsToDict();
        loadWordsToPrac();
        showDictionary();
//        configuration.mainMenu();
    }

   private void showDictionary() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Dictionary.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void loadWordsToDict(){
        dictionaryWords =  configuration.dictionary.displayWords(configuration.dictionary.dictEnglishMap);
    }
    public void loadWordsToPrac(){
        pracWords =  configuration.myWords.displayWords(configuration.myWords.pracEnglishMap);
    }






    public static void showFlashCards() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UI.class.getResource("Flashcards.fxml"));
        BorderPane sc = loader.load();
        mainLayout.setCenter(sc);
    }

    public static void showGuessWord() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UI.class.getResource("GuessWord.fxml"));
        BorderPane sc = loader.load();
        mainLayout.setCenter(sc);
    }

    public static void showTranslateWord() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UI.class.getResource("TranslateWord.fxml"));
        BorderPane sc = loader.load();
        mainLayout.setCenter(sc);
    }

    public static void showMatchWord() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UI.class.getResource("MatchWords.fxml"));
        BorderPane sc = loader.load();
        mainLayout.setCenter(sc);
    }
//    public static void resetDict(Dictionary d) {
//        dictionary = d;
//        engDict = dictionary.getDictEnglishMap();
//        welDict = dictionary.getDictWelshMap();
//    }
    public static void resetDict(Dictionary d) {
        configuration.dictionary = d;
//        engDict = dictionary.getDictEnglishMap();
//        welDict = dictionary.getDictWelshMap();
    }
    public static void resetPracList(MyWords myWords) {
        configuration.myWords = myWords;

    }
    public void save(ObservableList<Word> dictList, ObservableList<Word> pracList){
        configuration.dictionary.saveList("Assets/dictionary.json", dictList);
        configuration.dictionary.saveList("Assets/practiceList.json", pracList);

    }
}
