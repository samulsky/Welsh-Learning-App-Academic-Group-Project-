/*
 * @(#) AddWordsController.java 1 2020/04/30
 *
 * Copyright (c) 2020 Aberystwyth University.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs211.group17.welshapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * AddWordsController - A class to control the add words window.
 * <p>
 * Used to control the window in which the user can add new word to the dictionary
 *
 * @author (name)
 * @version 1
 * @see DictionaryController
 */
public class AddWordsController extends DictionaryController implements Initializable{

    // //////////////// //
    // Class variables. //
    // //////////////// //
    public static ObservableList<String> wordTypes = FXCollections.observableArrayList("nm","nf","verb","other");

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    @FXML private TextField englishWordTextField;
    @FXML private TextField welshWordTextField ;
    @FXML private ChoiceBox<String> wordTypeChoiceBox = new ChoiceBox<>();
    @FXML private Text confirmationText;
    @FXML private Button addButton;
    private boolean added;

    // //////// //
    // Methods. //
    // //////// //
    public void newWordButtonPushed(){

        if(!englishWordTextField.getText().equals("")&&!welshWordTextField.getText().equals("")&&!wordTypeChoiceBox.getSelectionModel().isEmpty()) {

            if (added) {
                dialog.close();
            } else {
                if (wordTypeChoiceBox.getValue().equals("verb")) {
                    englishWordTextField.setText("To " + englishWordTextField.getText());
                }
                Word newWord = new Word(englishWordTextField.getText(),
                        welshWordTextField.getText(),
                        wordTypeChoiceBox.getValue());

                if (words.contains(newWord)) {
                    System.out.println("word already exists in the dictionary");
                    confirmationText.setText("word already exists in the dictionary");
                } else {

                    words.add(newWord);
                    System.out.println(newWord + " word added");
                    confirmationText.setText("word added");
                    addButton.setText("Close");
                    userinterface.save(words, wordsMy);
                    added = true;
                }

            }

        }
    }


    @Override
    public void initialize (URL location, ResourceBundle resources) {

        wordTypeChoiceBox.setItems(wordTypes);
        confirmationText.setText("");
        addButton.setText("Add Word");
        added = false;
    }

}
