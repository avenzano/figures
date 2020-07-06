package org.testapp.figure.util;

import java.math.BigDecimal;

public class FigureUtil {

	public static boolean isPositive(BigDecimal number) {
		return number != null && number.compareTo(BigDecimal.ZERO) > 0;
	}
}
