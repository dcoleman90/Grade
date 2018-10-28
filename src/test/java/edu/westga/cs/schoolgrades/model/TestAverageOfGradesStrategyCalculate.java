package edu.westga.cs.schoolgrades.model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;





public class TestAverageOfGradesStrategyCalculate {

	private static final double DELTA = 0.001;
	private Grade grade0;
	private Grade grade1;
	private Grade grade2;
	
	private List<Grade> grades;
	
	private AverageOfGradesStrategy strategy;
	
	@BeforeEach
	public void setup() {
		grade0 = new SimpleGrade(10);
		grade1 = new SimpleGrade(20);
		grade2 = new SimpleGrade(30);
		
		grades = new ArrayList<Grade>();
		
		strategy = new AverageOfGradesStrategy();
	}
	
	@Test
	public  void shouldNotAllowNullGradesList() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			strategy.calculate(null);
		});
	}
	
	@Test
	public void shouldGiveZeroIfNoGrades() {
		assertEquals(0, strategy.calculate(grades), DELTA);
	}
	
	@Test
	public void shouldCalculateAverageOfOneGrades() {
		grades.add(grade0);
		assertEquals(grade0.getValue(), strategy.calculate(grades), DELTA);
	}

	@Test
	public void shouldCalculateSumOManyGrades() {
		grades.add(grade0);
		grades.add(grade1);
		grades.add(grade2);
		assertEquals(20, strategy.calculate(grades), DELTA);
	}

}
