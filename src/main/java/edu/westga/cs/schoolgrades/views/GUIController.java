package edu.westga.cs.schoolgrades.views;

import javafx.scene.layout.GridPane;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;


public class GUIController extends GridPane {
	@FXML
	private ListView<Double> lvQuiz;
	@FXML
	private ListView<Double> lvHomework;
	@FXML
	private ListView<Double> lvExam;
	
	private ObservableList<Double> quiz;
	private ObservableList<Double> homework;
	private ObservableList<Double> exam;
	
	public void initialize() {}
	
	public GUIController() {
		this.quiz =  FXCollections.observableArrayList();
		this.homework =  FXCollections.observableArrayList();
		this.exam =  FXCollections.observableArrayList();
		
		this.lvExam = new ListView<Double>();
		this.lvHomework = new ListView<Double>();
		this.lvQuiz = new ListView<Double>();
		
		this.buildSchoolGrade();
	}
	
	public void buildSchoolGrade() {
		this.buildGaps();
		this.buildQuiz();
	}
	
	private void buildGaps() {
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(5, 5, 5, 5));
	}
	
	private void buildQuiz() {
		this.lvQuiz.setItems(this.quiz);
		this.lvQuiz.setTooltip(new Tooltip("Quizes"));
		
	//	this.quiz.addListener(new ListChangeListener<Item>);
		
		this.add(lvQuiz, 0, 0);
	}
	
	
}
