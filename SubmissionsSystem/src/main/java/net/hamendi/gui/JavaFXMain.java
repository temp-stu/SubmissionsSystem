package net.hamendi.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class JavaFXMain extends Application {
	
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) {
        
        try {
        	
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SubmissionsSystem.fxml"));
        	
        	AnchorPane page = (AnchorPane) fxmlLoader.load();
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Submissions System - Diploma Project");
            primaryStage.show();
            
            
            
            SubmissionsSystem controller = fxmlLoader.getController();
        	Pane codePane = controller.getCodeText();
        	final SwingNode swingNode = new SwingNode();
    		setCodePane(swingNode, (int)(codePane.getWidth()/10), (int)(codePane.getHeight()/10));
            codePane.getChildren().add(swingNode);
            
            codePane.widthProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					codePane.getChildren().remove(swingNode);
					setCodePane(swingNode, newValue.intValue()/10, (int)(codePane.getHeight()/10.0));
					codePane.getChildren().add(swingNode);
				}
			});
            codePane.heightProperty().addListener(new ChangeListener<Number>() {
                @Override 
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                	codePane.getChildren().remove(swingNode);
                	setCodePane(swingNode, (int)(codePane.getWidth())/10, newValue.intValue()/10);
                	codePane.getChildren().add(swingNode);
                }
            });

        } catch (Exception ex) {
            Logger.getLogger(JavaFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }

		/*
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
        	try {
        		Desktop desktop = Desktop.getDesktop();
	            desktop.open(file);
	        } catch (IOException ex) {
	            
	        }
        }
        */
		
	}

	private void setCodePane(final SwingNode swingNode, int width, int height) {
		JPanel cp = new JPanel(new GridBagLayout());
        RSyntaxTextArea textArea = new RSyntaxTextArea(width, height); //32,45
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setAntiAliasingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        sp.setFoldIndicatorEnabled(true);
        cp.add(sp);
        
        swingNode.setContent(cp);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
