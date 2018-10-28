package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCompositeGradeGetValue {

	@Test
	void shouldGetValue() {
		CompositeGrade composite = new CompositeGrade(new SumOfGradesStrategy());
		composite.add(new SimpleGrade(10));
		composite.add(new SimpleGrade(20));
		composite.add(new SimpleGrade(30));
		assertEquals(60, composite.getValue(), 0.01);
	}

}
