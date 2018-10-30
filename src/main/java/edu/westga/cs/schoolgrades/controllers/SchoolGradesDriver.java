package edu.westga.cs.schoolgrades.controllers;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SchoolGradesDriver extends Application {

	private static final String GUI_RESOURCE = "edu/westga/cs/schoolgrades/views/GradesGUI.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(SchoolGradesDriver.GUI_RESOURCE);
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grades Worksheet");
        primaryStage.show();
    }

    /**
     * Start point for the application.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }

}

