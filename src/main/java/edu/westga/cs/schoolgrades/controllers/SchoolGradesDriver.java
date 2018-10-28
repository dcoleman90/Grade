package edu.westga.cs.schoolgrades.controllers;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SchoolGradesDriver extends Application {

	private static final String GUI_RESOURCE = "edu.westga.cs.schoolgrades.views.GUISchoolGrades.fxml";

	@Override
	public void start(Stage primaryStage) throws Exception {

	//	FXMLLoader loader = new FXMLLoader(getClass().getResource("edu.westga.cs.schoolgrades.views.GUISchoolGrades.fxml"));
		Parent root = (Parent)FXMLLoader.load(SchoolGradesDriver.class.getResource("edu.westga.cs.schoolgrades.views.GUISchoolGrades.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Start point for the application.
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
