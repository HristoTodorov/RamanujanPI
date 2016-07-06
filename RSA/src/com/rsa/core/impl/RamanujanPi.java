package com.rsa.core.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.rsa.core.AlgorithmUtils;
import com.rsa.core.CommonConstants;
import com.rsa.core.FactorialComputation;
import com.rsa.core.Statistics;

public class RamanujanPi implements Runnable {

	private BigDecimal finalResult = null;

	private int start;

	private int end;

	private boolean isQuite;
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public RamanujanPi(int start, int end, boolean isQuite) {
		this.start = start;
		this.end = end;
		this.isQuite = isQuite;
	}

	public BigDecimal getResult() {
		return finalResult;
	}

	/**
	 * Compute the denomerator of the series of Ramanujan.
	 * @param n the n-th member of the series
	 * @return the denomerator
	 */
	private BigInteger computeDenom(int n) {
		BigInteger result = BigInteger.ONE;
		//4^n
		result = result.multiply(AlgorithmUtils.quickExpo(BigInteger.valueOf(4),
				BigInteger.valueOf(n))); //4^n
		//n!
		result = result.multiply(
				FactorialComputation.factorial(BigInteger.valueOf(n)));
		//^4
		result = AlgorithmUtils.quickExpo(result, BigInteger.valueOf(4));
		//882 ^ (2n)
		result = result.multiply(
				AlgorithmUtils.quickExpo(BigInteger.valueOf(882), 
						BigInteger.valueOf(2 * n)));
		return result;
	}

	private BigInteger computeNom(int n) {
		BigInteger result = BigInteger.ONE;
		//Dealing with (-1)^n
		if (n % 2 != 0) {
			result = result.multiply(BigInteger.valueOf(-1));
		}

		result = result.multiply(FactorialComputation.factorial(
				BigInteger.valueOf(n).multiply(BigInteger.valueOf(4))));

		result = result.multiply((BigInteger.valueOf(1123).add(
				BigInteger.valueOf(21460).multiply(BigInteger.valueOf(n)))));
		return result;
	}

	/**
	 * Compute the serie from <b> start </b> to <b> end </b>.
	 */
	public BigDecimal computeSerie(){
		BigDecimal result = new BigDecimal(0);
		for (int i = start; i < end; i++) {
			result = result.add((new BigDecimal(computeNom(i)))
					.divide(new BigDecimal(computeDenom(i)), 
					CommonConstants.PRECISION, CommonConstants.MODE));
		}
		return result;
	}

	@Override
	public void run() {
		final String threadName = Thread.currentThread().getName();
		Statistics stats = new Statistics(threadName, System.currentTimeMillis(), isQuite);
		try {
			stats.logStart();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finalResult = computeSerie();
		stats.setEndTime(System.currentTimeMillis());
		try {
			stats.logEnd();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
