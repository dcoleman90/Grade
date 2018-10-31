package edu.westga.cs.schoolgrades.views;

import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import javafx.scene.control.TextField;
import java.util.Optional;



import edu.westga.cs.schoolgrades.model.AverageOfGradesStrategy;
import edu.westga.cs.schoolgrades.model.CompositeGrade;
import edu.westga.cs.schoolgrades.model.Grade;
import edu.westga.cs.schoolgrades.model.SimpleGrade;
import edu.westga.cs.schoolgrades.model.SumOfGradesStrategy;
import edu.westga.cs.schoolgrades.model.WeightedGrade;
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
	@FXML
	private TextField quizTotal;
	@FXML
	private TextField homeworkTotal;
	@FXML
	private TextField examTotal;
	@FXML
	private TextField total;
	private CompositeGrade everyQuiz;
	private CompositeGrade everyHomework;
	private CompositeGrade everyExam;
	private CompositeGrade finalGrade;
	private AverageOfGradesStrategy averageStrat;
	private SumOfGradesStrategy sumStrategy;
	
	public GUIController() {
		this.averageStrat = new AverageOfGradesStrategy();
		this.sumStrategy = new SumOfGradesStrategy();
		this.quiz = FXCollections.observableArrayList();
		this.homework = FXCollections.observableArrayList();
		this.exam = FXCollections.observableArrayList();

		this.lvExam = new ListView<Double>();
		this.lvHomework = new ListView<Double>();
		this.lvQuiz = new ListView<Double>();
		
		this.homeworkTotal = new TextField();
		this.examTotal = new TextField();
		this.quizTotal = new TextField();
		this.total = new TextField();
		
		this.everyQuiz = new CompositeGrade(sumStrategy);
		this.everyExam = new CompositeGrade(averageStrat);
		this.everyHomework = new CompositeGrade(averageStrat);
		this.finalGrade = new CompositeGrade(averageStrat);
		
		this.lvQuiz.setItems(this.quiz);
		this.lvExam.setItems(this.exam);
		this.lvHomework.setItems(this.homework);
		
		this.setToolTips();
	}

	
	private void addQuiz(Grade gradeAdded) {
		this.quiz.add(gradeAdded.getValue());
		this.lvQuiz.setItems(this.quiz);
		this.everyQuiz.add(gradeAdded);
		this.quizTotal.setText("" + this.everyQuiz.getValue());
	}	
	
	
	private void addExam(Grade gradeAdded) {
		this.exam.add(gradeAdded.getValue());
		this.lvExam.setItems(this.exam);
		this.everyExam.add(gradeAdded);
		String everyExamString = "" + this.everyExam.getValue();
		this.examTotal.setText("" + this.everyExam.getValue());
	}
	
	private void addHomework(Grade gradeAdded) {
		this.homework.add(gradeAdded.getValue());
		this.lvHomework.setItems(this.homework);
		this.everyHomework.add(gradeAdded);
		this.homeworkTotal.setText( "" + this.everyHomework.getValue());
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
	
	@FXML
	private void addExamButton() {
		double ExamAdded;
		TextInputDialog addExam = new TextInputDialog("Exam Grade in numeric form");
		addExam.setTitle("Add Exam result");
		addExam.setHeaderText("Please insert a Exam Grade result");
		addExam.setContentText("Please enter your value here");
		Optional<String> result = addExam.showAndWait();
		if (result != null) {
			ExamAdded = this.getUserSelectedDouble(result.get());
			if (ExamAdded < 0) {
				this.incorrectValueAlert();
			} else {
				this.addExam(this.addNewGrade(ExamAdded));
			}
		}
	}
	
	@FXML
	private void addHomeworkButton() {
		double HomeworkAdded;
		TextInputDialog addHomework = new TextInputDialog("Homework Grade in numeric form");
		addHomework.setTitle("Add Homework result");
		addHomework.setHeaderText("Please insert a Homework Grade result");
		addHomework.setContentText("Please enter your value here");
		Optional<String> result = addHomework.showAndWait();
		if (result != null) {
			HomeworkAdded = this.getUserSelectedDouble(result.get());
			if (HomeworkAdded < 0) {
				this.incorrectValueAlert();
			} else {
				this.addHomework(this.addNewGrade(HomeworkAdded));
			}
		}
	}
	
	@FXML
	private void setFinalGrade() {
		this.finalGrade = new CompositeGrade(this.sumStrategy);
		WeightedGrade weightQuiz = new WeightedGrade(this.everyQuiz, 0.2);
		WeightedGrade weightHW = new WeightedGrade(this.everyHomework, 0.3);
		WeightedGrade weightExam = new WeightedGrade(this.everyExam, 0.5);
		this.finalGrade.add(weightQuiz);
		this.finalGrade.add(weightHW);
		this.finalGrade.add(weightExam);
		this.total.setText("" + this.finalGrade.getValue());
	}

	@FXML
	private void setToolTips() {
		Tooltip listViewExam = new Tooltip("This field shows all the Exam values");
		Tooltip listViewQuiz = new Tooltip("This field shows all the Quiz values");
		Tooltip listViewHW = new Tooltip("This field shows all the Homework values");
		this.lvExam.setTooltip(listViewExam);
		this.lvQuiz.setTooltip(listViewQuiz);
		this.lvHomework.setTooltip(listViewHW);

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