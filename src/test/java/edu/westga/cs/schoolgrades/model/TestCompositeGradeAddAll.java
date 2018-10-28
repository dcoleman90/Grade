package edu.westga.cs.schoolgrades.model;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCompositeGradeAddAll {

	private CompositeGrade composite;
	private Grade grade0;
	private Grade grade1;
	private Grade grade2;
	private List<Grade> list;
	
	@BeforeEach
	public void setup() {
		composite = new CompositeGrade(new SumOfGradesStrategy());
		grade0 = new SimpleGrade(10);
		grade1 = new SimpleGrade(20);
		grade2 = new SimpleGrade(30);
		list = new ArrayList<Grade>();
	}
	

	@Test
	public void shouldNotAddNullGradesList() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			composite.addAll(null);
		});
	}
	
	@Test
	public void shouldAddEmptyList() {
		composite.addAll(new ArrayList<Grade>());
		assertTrue(composite.getGrades().isEmpty());
	}
	
	@Test
	public void shouldAddOneElementList() {
		list.add(grade0);
		composite.addAll(list);
		List<Grade> actual = composite.getGrades();
		assertEquals(1, actual.size());
		assertEquals(grade0, actual.get(0));
	}
	
	@Test
	public void shouldAddManyElementsList() {
		list.add(grade0);
		list.add(grade1);
		list.add(grade2);
		composite.addAll(list);
		List<Grade> actual = composite.getGrades();
		assertEquals(3, actual.size());
		assertEquals(grade0, actual.get(0));
		assertEquals(grade1, actual.get(1));
		assertEquals(grade2, actual.get(2));
	}

	@Test
	public void shouldNotAddListWithDuplicates() {
		list.add(grade0);
		list.add(grade1);
		list.add(grade0);
		list.add(grade2);
		
		assertThrows(IllegalArgumentException.class, () ->{ 
			composite.addAll(list);
		});
	}
	
	@Test
	public void shouldNotAddListThatDuplicatesExistingGrades() {
		composite.add(grade1);
		list.add(grade1);
		list.add(grade0);
		list.add(grade2);
		
		assertThrows(IllegalArgumentException.class, () ->{ 
			composite.addAll(list);
		});
	}
}
