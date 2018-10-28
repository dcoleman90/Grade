package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWeightedGradeConstructor {

	@Test
	public void shouldNotAllowNullGrade() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			new WeightedGrade(null, 10.0);
		});
	}
	
	@Test
	public void shouldNotAllowNegativeWeight() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			new WeightedGrade(new SimpleGrade(50), -10.0);
		});
	}
	
	@Test
	public void shouldNotAllowWeightGreaterThanOne() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			new WeightedGrade(new SimpleGrade(50), 10.0);
		});
	}
}
