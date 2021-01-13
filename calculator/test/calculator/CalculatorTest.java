package calculator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	private Calculator cal;

	@Before //테스트 시 독립성 보장
	public void setup() {
		cal = new Calculator();
		System.out.println("setup");
	}

	@Test
	public void add() {
		assertEquals(3, cal.add(2, 1));
		System.out.println("add");
	}

	@Test
	public void divide() {
		assertEquals(3, cal.divide(9, 3));
		System.out.println("divide");
	}

	@After
	public void teardown() {
		System.out.println("teardown");
	}

}
