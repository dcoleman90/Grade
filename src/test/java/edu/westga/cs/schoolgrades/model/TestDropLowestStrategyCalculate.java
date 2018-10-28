package edu.westga.cs.schoolgrades.model;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDropLowestStrategyCalculate {

	private DropLowestStrategy dropLowestStrategy;
	private GradeCalculationStrategy childStrategy;
	
	private static final double DELTA = 0.001;
	private Grade grade0;
	private Grade grade1;
	private Grade grade2;
	
	private List<Grade> grades;
	
	@BeforeEach
	public void setUp() throws Exception {
		grade0 = new SimpleGrade(10);
		grade1 = new SimpleGrade(20);
		grade2 = new SimpleGrade(30);
		
		grades = new ArrayList<Grade>();
		
		childStrategy = new SumOfGradesStrategy();
		dropLowestStrategy = new DropLowestStrategy(childStrategy);
	}

	@Test
	public void shouldNotAllowNullGradesList() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			dropLowestStrategy.calculate(null);
		});
	}

	@Test
	public void shouldNotDropLowestIfGradesListIsEmpty() {
		assertEquals(0, dropLowestStrategy.calculate(grades), DELTA);
	}
	
	public void shouldNotDropLowestIfGradesListHasOneElement() {
		grades.add(grade0);
		assertEquals(grade0.getValue(), dropLowestStrategy.calculate(grades), DELTA);
	}
	
	@Test
	public void canDropWhenLowestIsFirst() {
		grades.add(grade0);
		grades.add(grade1);
		grades.add(grade2);
		assertEquals(50, dropLowestStrategy.calculate(grades), DELTA);
	}
	
	
	@Test
	public void canDropWhenLowestIsLast() {
		grades.add(grade1);
		grades.add(grade2);
		grades.add(grade0);
		assertEquals(50, dropLowestStrategy.calculate(grades), DELTA);
	}
	
	@Test
	public void canDropWhenLowestIsInMiddle() {
		grades.add(grade1);
		grades.add(grade0);
		grades.add(grade2);
		assertEquals(50, dropLowestStrategy.calculate(grades), DELTA);
	}
	
	@Test
	public void dropsOnlyOneIfThereAreMultipleLowestGrades() {
		grades.add(grade1);
		grades.add(grade0);
		grades.add(grade2);
		grades.add(grade0);
		assertEquals(60, dropLowestStrategy.calculate(grades), DELTA);
	}
}
