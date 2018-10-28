package edu.westga.cs.schoolgrades.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDropLowestStrategyConstructor {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void shouldNotAllowNullChildStrategy() {
		assertThrows(IllegalArgumentException.class, () ->{ 
			new DropLowestStrategy(null);
		});
	}
}
