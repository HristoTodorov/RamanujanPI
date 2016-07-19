package com.rsa.core;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class Result {
	private static BigDecimal RESULT = BigDecimal.ZERO;
	
	public static void add(BigDecimal member) {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		ReadLock readLock = lock.readLock();
		readLock.lock();
		try {
			RESULT = RESULT.add(member);
		} finally {
			readLock.unlock();
		}
	}
	
	public static BigDecimal get() {
		return RESULT;
	}
}
