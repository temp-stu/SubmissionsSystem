package net.hamendi.gui;

/**
 * Sample Skeleton for 'SubmissionsSystem.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

public class SubmissionsSystem {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addItem"
    private Button addItem; // Value injected by FXMLLoader

    @FXML // fx:id="removeItem"
    private Button removeItem; // Value injected by FXMLLoader

    @FXML // fx:id="treeView"
    private TreeView<?> treeView; // Value injected by FXMLLoader

    @FXML // fx:id="runSelected"
    private Button runSelected; // Value injected by FXMLLoader

    @FXML // fx:id="codeText"
    private Pane codeText; // Value injected by FXMLLoader

    @FXML // fx:id="runAll"
    private Button runAll; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addItem != null : "fx:id=\"addItem\" was not injected: check your FXML file 'SubmissionsSystem.fxml'.";
        assert removeItem != null : "fx:id=\"removeItem\" was not injected: check your FXML file 'SubmissionsSystem.fxml'.";
        assert treeView != null : "fx:id=\"treeView\" was not injected: check your FXML file 'SubmissionsSystem.fxml'.";
        assert runSelected != null : "fx:id=\"runSelected\" was not injected: check your FXML file 'SubmissionsSystem.fxml'.";
        assert codeText != null : "fx:id=\"codeText\" was not injected: check your FXML file 'SubmissionsSystem.fxml'.";
        assert runAll != null : "fx:id=\"runAll\" was not injected: check your FXML file 'SubmissionsSystem.fxml'.";
        
    }
    
    public Pane getCodeText() {
    	return codeText;
    }
}
