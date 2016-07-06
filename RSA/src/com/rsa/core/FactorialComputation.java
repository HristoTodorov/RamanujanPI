package com.rsa.core;

import java.math.BigInteger;
import java.util.function.Function;

public class FactorialComputation {
	private static final Function<BigInteger, BigInteger> CACHED = Memoizer.memoize(FactorialComputation::uncached);

	public static BigInteger factorial(BigInteger n) {
		return CACHED.apply(n);
	}

	private static BigInteger uncached(BigInteger n) {
		BigInteger result = BigInteger.ONE;
	    while (!n.equals(BigInteger.ZERO)) {
	        result = result.multiply(n);
	        n = n.subtract(BigInteger.ONE);
	    }
	    return result;
	}
}
