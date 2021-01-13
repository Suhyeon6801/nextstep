package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	int add(String text) {
		if (isBlank(text)) {
			return 0;
		}

		return sum(toInt(split(text)));
	}

	/**
	 * 빈문자열 혹은 null인지 확인하는 함수
	 * @param text
	 * @return boolean
	 */
	private boolean isBlank(String text) {
		return (text == null || text.isEmpty());
	}

	/**
	 * 배열의 합을 구하는 함수
	 * @param numbers
	 * @return int
	 */
	private int sum(int[] numbers) {
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}

		return sum;
	}

	/**
	 * String배열을 int 배열로 바꿔주는 함수
	 * @param values
	 * @return int[]
	 */
	private int[] toInt(String[] values) {
		int[] numbers = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			numbers[i] = toPositive(values[i]);
		}
		return numbers;
	}

	/**
	 * String을 양수만 바꿔주는 함수
	 * <p>
	 * 음수일 경우 runtimeException
	 * 
	 * @param value
	 * @return int
	 */
	private int toPositive(String value) {
		int number = Integer.parseInt(value);
		if (number < 0) {
			throw new RuntimeException();
		}

		return number;
	}

	/**
	 * String을 String배열로 분할하는 함수
	 * @param text
	 * @return String[]
	 */
	private String[] split(String text) {
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
		if (m.find()) {
			String customDelimeter = m.group(1);
			return m.group(2).split(customDelimeter);
		}
		return text.split(",|;");
	}
}
