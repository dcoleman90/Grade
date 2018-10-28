package edu.westga.cs.schoolgrades.controllers;

import edu.westga.cs.schoolgrades.views.GUIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SchoolGradesDriver extends Application {
	@Override
	public void start(Stage primaryStage) {
		GUIController grades = new GUIController();
		BorderPane pane = new BorderPane();
		pane.setCenter(grades);
		Scene display = new Scene(pane, 300, 250);
		primaryStage.setTitle("SchoolGrade");
		primaryStage.setScene(display);
		primaryStage.show();

	}

	/**
	 * This is the main method which launches the graphic
	 * 
	 * @param args
	 *            is the argument
	 */
	public static void main(String[] args) {
		launch(args);
	}
}