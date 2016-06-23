package com.rsa.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonConstants {
	
	/** The mode. */
	public static final RoundingMode MODE = RoundingMode.HALF_EVEN;

	/** The precision accurance for dividing operations. */
	public static final int PRECISION = 50;

	/** The final residual for Pi Ramanujan 2 formula. */
	public static final BigDecimal FINAL_RESIDUAL = BigDecimal.valueOf(1).
			divide(BigDecimal.valueOf(882), PRECISION, MODE);
	
	public static final String DEFAULT_OUTPUT_FILE = "result.txt";
}
