package com.rsa.core.utils;

import java.math.BigInteger;

public class QuickExpo {

	public static BigInteger compute(BigInteger base, BigInteger exponent) {
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

}
