package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSimpleGradeConstructor {

	private static final double DELTA = 0.001;

	@Test
	public void shouldNotAllowNegativeGrades() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			new SimpleGrade(-1);
		});
	}
	
	@Test
	public void shouldAllowZeroValue() {
		SimpleGrade grade = new SimpleGrade(0);
		assertEquals(0, grade.getValue(), DELTA);
	}
	
	@Test
	public void shouldAllowPositiveValue() {
		SimpleGrade grade = new SimpleGrade(50.0);
		assertEquals(50.0, grade.getValue(), DELTA);
	}

}
