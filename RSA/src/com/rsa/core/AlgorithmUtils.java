package com.rsa.core;

import java.math.BigDecimal;

/**
 * An utility class that provide an efficient algorithms implementations.
 * 
 * @author Hristo G. Todorov
 * <p>FMI, Sofia, 2016</p>
 * <p><b>GNU General Public Licence v2.0</b></p>
 *
 */
public class AlgorithmUtils {
	/**
	 * Compute the factorial of n
	 * @param n the number
	 * @return the result
	 */
	public static BigDecimal factorial(BigDecimal n) {
	    BigDecimal result = BigDecimal.ONE;
	    while (!n.equals(BigDecimal.ZERO)) {
	        result = result.multiply(n);
	        n = n.subtract(BigDecimal.ONE);
	    }
	    return result;
	}
	
}
