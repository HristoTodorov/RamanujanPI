package com.rsa.core.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class Memoizer {
	public static <I, O> Function<I, O> memoize(Function<I, O> f) {
	    ConcurrentMap<I, O> lookup = new ConcurrentHashMap<>();
	    ReentrantLock lock = new ReentrantLock();
	    return input -> {
	    	lock.lock();
	    	try {
	    		return lookup.computeIfAbsent(input, f);
	    	} finally {
	    		lock.unlock();
	    	}
	    };
	}
}
