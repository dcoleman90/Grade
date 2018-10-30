package edu.westga.cs.schoolgrades.views;

import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.util.Optional;

import edu.westga.cs.schoolgrades.model.Grade;
import edu.westga.cs.schoolgrades.model.SimpleGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TextInputDialog;

public class GUIController extends GridPane {
	@FXML
	private ListView<Double> lvQuiz;
	@FXML
	private ListView<Double> lvHomework;
	@FXML
	private ListView<Double> lvExam;
	@FXML
	private ObservableList<Double> quiz;
	@FXML
	private ObservableList<Double> homework;
	@FXML
	private ObservableList<Double> exam;

	public GUIController() {
		this.quiz = FXCollections.observableArrayList();
		this.homework = FXCollections.observableArrayList();
		this.exam = FXCollections.observableArrayList();

		this.lvExam = new ListView<Double>();
		this.lvHomework = new ListView<Double>();
		this.lvQuiz = new ListView<Double>();

		this.lvQuiz.setItems(this.quiz);
		this.lvExam.setItems(this.exam);
		this.lvHomework.setItems(this.homework);
	}

	@FXML
	private void addQuiz(Grade gradeAdded) {
		this.quiz.add(gradeAdded.getValue());
		this.lvQuiz.setItems(this.quiz);
	}	

	@FXML
	private void addQuizButton() {
		double quizAdded;
		TextInputDialog addQuiz = new TextInputDialog("Quiz Grade in numeric form");
		addQuiz.setTitle("Add Quiz result");
		addQuiz.setHeaderText("Please insert a Quiz Grade result");
		addQuiz.setContentText("Please enter your value here");
		Optional<String> result = addQuiz.showAndWait();
		if (result != null) {
			quizAdded = this.getUserSelectedDouble(result.get());
			if (quizAdded < 0) {
				this.incorrectValueAlert();
			} else {
				this.addQuiz(this.addNewGrade(quizAdded));
			}
		}
	}

	private SimpleGrade addNewGrade(Double gradeAdded) {
		SimpleGrade grade = new SimpleGrade(gradeAdded);
		return grade;
	}
	
	private double getUserSelectedDouble(String newStartingValue) {
		double userEnteredValue = -5;
		try {
			userEnteredValue = Double.parseDouble(newStartingValue);
		} catch (IllegalArgumentException iae) {
			userEnteredValue = -5;
		}
		return userEnteredValue;
	}
	
	private void incorrectValueAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Incorrect Value");
		alert.setHeaderText("All Added Grades must be in numeric form");
		alert.setContentText("Values cannot be negitive numbers");
		alert.showAndWait();
	}

}