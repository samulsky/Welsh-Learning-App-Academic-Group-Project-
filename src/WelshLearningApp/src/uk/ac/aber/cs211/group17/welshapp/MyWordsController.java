/*
 * @(#) MyWordsController.java 1 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * MyWordsController - A class to control window which displays the user practice list.
 * <p>
 * Used to control the actions the user can perform in their practice list
 *
 * @author (name)
 * @version 1
 * @see GuessWordController
 * @see PracticeController
 * @see MatchWordsController
 * @see TranslateWordController
 */

public class MyWordsController extends DictionaryController implements Initializable {

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    @FXML public TableView<Word> tableViewMyWords;
    @FXML private TableColumn<Word,String> englishWordMyWords;
    @FXML private TableColumn<Word,String> welshWordMyWords;
    @FXML private TableColumn<Word,String> wordTypeMyWords;
    @FXML private TableColumn<Word, Void> colBtnMyWords;
//    private ObservableList<Word> wordsMy = FXCollections.observableArrayList();
    @FXML private TextField searchFieldMyWords;


    // //////// //
    // Methods. //
    // //////// //
    public void goDictionary() throws IOException {
        UI.backToDictionary();
    }

    private void searchForWords() {

        FilteredList<Word> filteredList = new FilteredList<>(wordsMy, b -> true);

        searchFieldMyWords.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(word -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (word.getEnglishWord().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (word.getWelshWord().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (word.getWordType().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false;
            });
        });

        SortedList<Word> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableViewMyWords.comparatorProperty());
        tableViewMyWords.setItems(sortedList);
    }

    private void addButtons(){


        Callback<TableColumn<Word,Void>, TableCell<Word,Void>> cellFactory
                = new Callback<TableColumn<Word, Void>, TableCell<Word, Void>>() {
            @Override
            public TableCell<Word, Void> call(TableColumn<Word, Void> param) {
                final TableCell<Word,Void> cell = new TableCell<Word,Void>(){
                    final Button btn = new Button("Remove");

                    @Override
                    public void updateItem(Void item, boolean empty){
                        super.updateItem(item,empty);
                        if (empty){
                            setGraphic(null);
                            setText(null);
                        }else {
                            btn.setOnAction(event -> {
                                Word word = getTableView().getItems().get(getIndex());
                                if (wordsMy.contains(word)){
                                    wordsMy.remove(new Word(word.getEnglishWord(), word.getWelshWord(), word.getWordType()));
                                    System.out.println("Removed " + word.getEnglishWord() + " from the practice list");

                                }
                                else{
                                    System.out.println("Error");
                                }
//                                System.out.println(word.getEnglishWord()+word.getWelshWord()+word.getWordType());


                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        colBtnMyWords.setCellFactory(cellFactory);
        //tableView.getColumns().add(colBtn);


    }

    public void Exit() {
        userinterface.save(words, wordsMy);
        Platform.exit();
        System.exit(0);
    }

    public void goPractice() throws Exception{
        UI.showPractice();
        userinterface.save(words, wordsMy);
    }

    private ObservableList<Word> getWords(){
        ArrayList<Word> wordArrayList = userinterface.pracWords;

        for (Word word : wordArrayList) {

            wordsMy.add(new Word(word.getEnglishWord(), word.getWelshWord(), word.getWordType()));
//            counter++;

        }

//        words1.add(new Word("abbey1 ","abaty1 ","nm"));
//        words1.add(new Word("about to1 ","ar fin1 ","other"));
//        words1.add(new Word("above1 ","uwchben1 ","other"));
//        words1.add(new Word("abbey12 ","abaty12 ","nm"));
//        words1.add(new Word("about to12 ","ar fin12 ","other"));
//        words1.add(new Word("above12 ","uwchben12 ","other"));
//        words1.add(new Word("abbey23 ","abaty23 ","nm"));
//        words1.add(new Word("about to23 ","ar fin23 ","other"));
//        words1.add(new Word("above23 ","uwchben2 ","other"));
//        words1.addAll(wordsMy);

        return wordsMy;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (wordsMy.isEmpty()){
            userinterface.loadWordsToPrac();
        }
        englishWordMyWords.setCellValueFactory(new PropertyValueFactory<>("englishWord"));
        welshWordMyWords.setCellValueFactory(new PropertyValueFactory<>("welshWord"));
        wordTypeMyWords.setCellValueFactory(new PropertyValueFactory<>("wordType"));

        tableViewMyWords.setItems(getWords());
        addButtons();
        searchForWords();
    }
}


