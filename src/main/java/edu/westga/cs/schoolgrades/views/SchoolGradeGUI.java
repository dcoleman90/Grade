package edu.westga.cs.schoolgrades.views;

import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;

public class SchoolGradeGUI extends GridPane {
	private ListView<Double> lvQuiz;
	private ListView<Double> lvHomework;
	private ListView<Double> lvExam;
	
	private ObservableList<Double> quiz;
	private ObservableList<Double> homework;
	private ObservableList<Double> exam;
	
	
	public SchoolGradeGUI() {
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
		this.lvQuiz.setItems(quiz);
		this.lvQuiz.setTooltip(new Tooltip("Quizes"));
		
		this.add(lvQuiz, 0, 0);
	}
}
