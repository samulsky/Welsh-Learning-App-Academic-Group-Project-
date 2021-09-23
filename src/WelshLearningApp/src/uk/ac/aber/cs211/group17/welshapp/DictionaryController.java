/*
 * @(#) Dictionary.java 1 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * DictionaryController - A class to control the main dictionary window.
 * <p>
 * Used to control the window in the dictionary is displayed and also deals with
 * searching and sorting displaying of words in the dictionary
 *
 * @author (name)
 * @version 1
 */

public class DictionaryController implements Initializable {
    // ////////// //
    // Constants. //
    // ////////// //
    final static Stage dialog = new Stage();


    // //////////////// //
    // Class variables. //
    // //////////////// //
    public static ObservableList<Word> words = FXCollections.observableArrayList();
    public static ObservableList<Word> wordsMy = FXCollections.observableArrayList();

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    @FXML public TableView<Word> tableViewDictionary;
    @FXML public TableColumn<Word,String> englishWord;
    @FXML public TableColumn<Word,String> welshWord;
    @FXML public TableColumn<Word,String> wordType;
    @FXML public TableColumn<Word, Void> colBtn;
    @FXML private TextField searchField;
    public UI userinterface = new UI();


    // //////// //
    // Methods. //
    // //////// //
    public void goMyWords() throws IOException {
        UI.showMyWords();
    }

    private void searchForWords() {


        FilteredList<Word> filteredList = new FilteredList<>(words, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(word -> {


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
//
                String lowerCaseFilter = newValue.toLowerCase();
//                dictionary.translateEnglish(lowerCaseFilter);
//                return true;
//
                if (word.getEnglishWord().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (word.getWelshWord().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return word.getWordType().contains(lowerCaseFilter);
            });
        });

        SortedList<Word> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableViewDictionary.comparatorProperty());
        tableViewDictionary.setItems(sortedList);
    }


    @FXML
    private void addWordsWindow() throws IOException {


        AnchorPane dialogVbox;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(UI.class.getResource("AddWords.fxml"));
        dialogVbox = loader.load();
        Scene dialogScene = new Scene(dialogVbox);
        dialog.setScene(dialogScene);
        dialog.setResizable(false);
        dialog.show();

    }

    private void addButtons(){

        Callback<TableColumn<Word,Void>, TableCell<Word,Void>> cellFactory
                = new Callback<TableColumn<Word, Void>, TableCell<Word, Void>>() {
            @Override
            public TableCell<Word, Void> call(TableColumn<Word, Void> param) {
                final TableCell<Word,Void> cell = new TableCell<Word,Void>(){
                    final Button btn = new Button("Save");


                    @Override
                    public void updateItem(Void item, boolean empty){
                        super.updateItem(item,empty);
                        if (empty){
                            setGraphic(null);
                            setText(null);
                        }else {
                            btn.setOnAction(event -> {
                                Word word = getTableView().getItems().get(getIndex());
                                // System.out.println(word.getEnglishWord()+word.getWelshWord()+word.getWordType());
                                if(wordsMy.contains(word)) {
                                    System.out.println("DONT ADD IT!!!");

                                }
                                else if (!wordsMy.contains(word)){
                                    wordsMy.add(new Word(word.getEnglishWord(), word.getWelshWord(), word.getWordType()));
                                    System.out.println("Added " + word.getEnglishWord() + " to the practice list");

                                }
                                else{
                                    System.out.println("ERROR");
                                }

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        //tableViewDictionary.getColumns().add(colBtn);    // crashes program keeping just in case


    }

//    private void saveToMyWords(){
//
//    }

    public void Exit() {

            userinterface.save(words, wordsMy);
        Platform.exit();
        System.exit(0);
    }


    private ObservableList<Word> getWords(){

        ArrayList<Word> wordArrayList = userinterface.dictionaryWords;

        for (Word word : wordArrayList) {

            words.add(new Word(word.getEnglishWord(), word.getWelshWord(), word.getWordType()));


        }


        return words;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (words.isEmpty()){
            userinterface.loadWordsToDict();
        }
//        resetDict(configuration.dictionary);
        englishWord.setCellValueFactory(new PropertyValueFactory<>("englishWord"));
        welshWord.setCellValueFactory(new PropertyValueFactory<>("welshWord"));
        wordType.setCellValueFactory(new PropertyValueFactory<>("wordType"));

        tableViewDictionary.setItems(getWords());
        addButtons();
        searchForWords();
    }
}



