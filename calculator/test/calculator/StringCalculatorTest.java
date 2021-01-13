package calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

	private StringCalculator cal;

	@Before
	public void setUp() {
		cal = new StringCalculator();
	}

	@Test
	public void addNullOrEmpty() {
		assertEquals(0, cal.add(""));
		assertEquals(0, cal.add(null));
	}

	@Test
	public void addOne() {
		assertEquals(1, cal.add("1"));
	}

	@Test
	public void addCommaOrSemicolon() {
		assertEquals(6, cal.add("1,2;3"));
	}

	@Test
	public void addCustomDelimeter() {
		assertEquals(6, cal.add("//;\n1;2;3"));
	}

	@Test(expected = RuntimeException.class)
	public void addNegative() {
		assertEquals(6, cal.add("-1;2,3"));
	}

}
