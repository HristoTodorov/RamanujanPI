package com.rsa.core;

import java.math.BigDecimal;
import java.math.BigInteger;

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

	public static BigInteger quickExpo(BigInteger base, BigInteger exponent) {
	    // plan: exploit the binary representation of the exponent, see for example http://en.wikipedia.org/w/index.php?title=Modular_exponentiation&oldid=517653653#Right-to-left_binary_method
	    BigInteger result = BigInteger.ONE;
	    if (exponent.equals(BigInteger.ZERO)) {
	    	return BigInteger.ONE;
	    }
	    while (exponent.compareTo(BigInteger.ZERO) > 0) {
	        if (exponent.testBit(0)) // then exponent is odd
	            result = (result.multiply(base));
	        exponent = exponent.shiftRight(1);
	        base = (base.multiply(base));
	    }
	    return result;
	}
	
	public static boolean isBlank(String string) {
		if (string != null) {
			return string.trim().isEmpty();
		} else {
			return true;
		}
	}
}
