package edu.westga.cs.schoolgrades.views;

import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;

import java.util.Optional;

import edu.westga.cs.schoolgrades.model.AverageOfGradesStrategy;
import edu.westga.cs.schoolgrades.model.CompositeGrade;
import edu.westga.cs.schoolgrades.model.DropLowestStrategy;
import edu.westga.cs.schoolgrades.model.Grade;
import edu.westga.cs.schoolgrades.model.GradeCalculationStrategy;
import edu.westga.cs.schoolgrades.model.SimpleGrade;
import edu.westga.cs.schoolgrades.model.SumOfGradesStrategy;
import edu.westga.cs.schoolgrades.model.WeightedGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
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
	private TextField tf_QuizTotal;
	@FXML
	private TextField tf_HomeworkTotal;
	@FXML
	private TextField tf_ExamTotal;
	@FXML
	private TextField total;

	private CompositeGrade quizSumTotal;
	private CompositeGrade HwDropLowAvg;
	private CompositeGrade examAvgTotal;
	private CompositeGrade finalGrade;

	private AverageOfGradesStrategy averageStrat;
	private SumOfGradesStrategy sumStrategy;
	private DropLowestStrategy dropAvgStrategy;

	public GUIController() {
		this.averageStrat = new AverageOfGradesStrategy();
		this.sumStrategy = new SumOfGradesStrategy();
		this.dropAvgStrategy = new DropLowestStrategy(this.averageStrat);

		this.quiz = FXCollections.observableArrayList();
		this.homework = FXCollections.observableArrayList();
		this.exam = FXCollections.observableArrayList();

		this.lvExam = new ListView<Double>();
		this.lvHomework = new ListView<Double>();
		this.lvQuiz = new ListView<Double>();

		this.tf_HomeworkTotal = new TextField();
		this.tf_ExamTotal = new TextField();
		this.tf_QuizTotal = new TextField();
		this.total = new TextField();

		this.quizSumTotal = new CompositeGrade(sumStrategy);
		this.examAvgTotal = new CompositeGrade(averageStrat);
		this.HwDropLowAvg = new CompositeGrade(this.dropAvgStrategy);
		this.finalGrade = new CompositeGrade(this.sumStrategy);

		this.lvQuiz.setItems(this.quiz);
		this.lvExam.setItems(this.exam);
		this.lvHomework.setItems(this.homework);
	}

	private void addGrade(ObservableList<Double> acceptedOL, CompositeGrade gradeAdded) {
		if (this.quiz == acceptedOL) {
			this.quiz.add(gradeAdded.getValue());
			this.quizSumTotal.add(gradeAdded);
			this.updateGrade(acceptedOL, this.quizSumTotal);
		} else if (this.exam == acceptedOL) {
			this.exam.add(gradeAdded.getValue());
			this.examAvgTotal.add(gradeAdded);
			this.updateGrade(acceptedOL, this.examAvgTotal);
		} else if (this.homework == acceptedOL) {
			this.homework.add(gradeAdded.getValue());
			this.HwDropLowAvg.add(gradeAdded);
			this.updateGrade(acceptedOL, this.HwDropLowAvg);
		} 
	}

	private void addQuiz(Grade gradeAdded) {
		this.quiz.add(gradeAdded.getValue());
		this.lvQuiz.setItems(this.quiz);
		this.quizSumTotal.add(gradeAdded);
		this.tf_QuizTotal.setText("" + this.quizSumTotal.getValue());
	}

	private void addExam(Grade gradeAdded) {
		this.exam.add(gradeAdded.getValue());
		this.lvExam.setItems(this.exam);
		this.examAvgTotal.add(gradeAdded);
		this.tf_ExamTotal.setText("" + this.examAvgTotal.getValue());
	}

	private void addHomework(Grade gradeAdded) {
		this.homework.add(gradeAdded.getValue());
		this.lvHomework.setItems(this.homework);
		this.HwDropLowAvg.add(gradeAdded);
		this.tf_HomeworkTotal.setText("" + this.HwDropLowAvg.getValue());
	}

	@FXML
	private void editQuizIndex() {
		if (this.lvQuiz.getSelectionModel().getSelectedIndex() >= 0) {
			this.editAlert(this.quiz, this.lvQuiz.getSelectionModel().getSelectedIndex(), this.quizSumTotal);
		}
	}

	@FXML
	private void editExamIndex() {
		if (this.lvExam.getSelectionModel().getSelectedIndex() >= 0) {
			this.editAlert(this.exam, this.lvExam.getSelectionModel().getSelectedIndex(), this.examAvgTotal);
		}
	}

	@FXML
	private void editHomeworkIndex() {
		if (this.lvHomework.getSelectionModel().getSelectedIndex() >= 0) {
			this.editAlert(this.homework, this.lvHomework.getSelectionModel().getSelectedIndex(), this.HwDropLowAvg);
		}
	}

	private void editAlert(ObservableList acceptedOL, int indexGrade, CompositeGrade acceptedGrade) {
		Alert edit = new Alert(AlertType.CONFIRMATION);
		edit.setTitle("Edit input");
		edit.setHeaderText("Would you like to change this Grade input?");
		edit.setContentText("Choose your selection");
		ButtonType editGrade = new ButtonType("Edit");
		ButtonType deleteGrade = new ButtonType("Delete");

		edit.getButtonTypes().setAll(editGrade, deleteGrade);

		Optional<ButtonType> selection = edit.showAndWait();
		if (selection.get() == editGrade) {
			this.editGradeLV(acceptedOL, indexGrade, acceptedGrade);
		} else if (selection.get() == deleteGrade) {
			this.deleteGradeLV(acceptedOL, indexGrade, acceptedGrade);
		}
	}

	private void editGradeLV(ObservableList<Double> acceptedOL, int indexGrade, CompositeGrade acceptedGrade) {
		TextInputDialog editGrade = new TextInputDialog("Quiz Grade in numeric form");
		editGrade.setTitle("Change Quiz result");
		editGrade.setHeaderText("Please insert a new Quiz Grade result");
		editGrade.setContentText("To delete result press ok");
		Optional<String> result = editGrade.showAndWait();
		GradeCalculationStrategy tempStrategy = acceptedGrade.getStrategy();
		System.out.println("Hey " + acceptedGrade + this.quizSumTotal);
		if (result != null) {
			Double quizAdded = this.getUserSelectedDouble(result.get());
			acceptedOL.set(indexGrade, quizAdded);
			if (quizAdded < 0) {
				this.incorrectValueAlert();
			} else {
				this.updateGrade(acceptedOL, acceptedGrade);

			}
		}
	}

	private void deleteGradeLV(ObservableList acceptedOL, int indexGrade, CompositeGrade acceptedGrade) {
		double removedGrade = (double) acceptedOL.get(indexGrade);
		acceptedOL.remove(indexGrade);
		this.updateGrade(acceptedOL, acceptedGrade);
		Alert delete = new Alert(AlertType.INFORMATION);
		delete.setTitle("deleted");
		delete.setHeaderText("The Grade " + removedGrade + " has been deleted");
		delete.showAndWait();

	}

	private void updateGrade(ObservableList acceptedOL, CompositeGrade acceptedGrade) {
		System.out.println(
				"4 " + acceptedGrade.getValue() + " " + acceptedGrade.toString() + this.quizSumTotal.toString());
		GradeCalculationStrategy tempStrategy = acceptedGrade.getStrategy();
		if (acceptedGrade == this.quizSumTotal) {
			System.out.println("quiz total");

			this.quizSumTotal = new CompositeGrade(tempStrategy);
			for (int count = 0; count < acceptedOL.size(); count++) {
				SimpleGrade temp = new SimpleGrade((Double) acceptedOL.get(count));
				this.quizSumTotal.add(temp);
			}
			this.tf_QuizTotal.setText("" + this.quizSumTotal.getValue());
		} else if (acceptedGrade == this.examAvgTotal) {
			this.examAvgTotal = new CompositeGrade(tempStrategy);
			for (int count = 0; count < acceptedOL.size(); count++) {
				SimpleGrade temp = new SimpleGrade((Double) acceptedOL.get(count));
				this.examAvgTotal.add(temp);
			}
			this.tf_ExamTotal.setText("" + this.examAvgTotal.getValue());
		} else if (acceptedGrade == this.HwDropLowAvg) {
			this.HwDropLowAvg = new CompositeGrade(tempStrategy);
			for (int count = 0; count < acceptedOL.size(); count++) {
				SimpleGrade temp = new SimpleGrade((Double) acceptedOL.get(count));
				this.HwDropLowAvg.add(temp);
			}
			this.tf_HomeworkTotal.setText("" + this.HwDropLowAvg.getValue());
		}
		this.lvQuiz.setItems(this.quiz);
		this.lvExam.setItems(this.exam);
		this.lvHomework.setItems(this.homework);
		this.setFinalGrade();
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
				System.out.println("1");
				this.addGrade(this.quiz, this.addNewGrade(quizAdded, this.sumStrategy));
			}
		}
	}

	@FXML
	private void addExamButton() {
		double examAdded;
		TextInputDialog addExam = new TextInputDialog("Exam Grade in numeric form");
		addExam.setTitle("Add Exam result");
		addExam.setHeaderText("Please insert a Exam Grade result");
		addExam.setContentText("Please enter your value here");
		Optional<String> result = addExam.showAndWait();
		if (result != null) {
			examAdded = this.getUserSelectedDouble(result.get());
			if (examAdded < 0) {
				this.incorrectValueAlert();
			} else {
				this.addGrade(this.exam, this.addNewGrade(examAdded, this.sumStrategy));
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
				this.addGrade(this.hoe, this.addNewGrade(quizAdded, this.sumStrategy));
			}
		}
	}

	@FXML
	private void setFinalGrade() {
		this.finalGrade = new CompositeGrade(this.sumStrategy);
		WeightedGrade weightQuiz = new WeightedGrade(this.quizSumTotal, 0.2);
		WeightedGrade weightHW = new WeightedGrade(this.HwDropLowAvg, 0.3);
		WeightedGrade weightExam = new WeightedGrade(this.examAvgTotal, 0.5);
		this.finalGrade.add(weightQuiz);
		this.finalGrade.add(weightHW);
		this.finalGrade.add(weightExam);
		this.total.setText("" + this.finalGrade.getValue());
	}

	private CompositeGrade addNewGrade(Double gradeAdded, GradeCalculationStrategy strategy) {
		SimpleGrade grade = new SimpleGrade(gradeAdded);
		CompositeGrade composite = new CompositeGrade(strategy);
		composite.add(grade);
		System.out.println("2 " + composite.getValue());
		return composite;
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
