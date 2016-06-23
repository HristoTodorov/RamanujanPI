package com.rsa.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Statistics {

	private long startTime;
	
	private long endTime;
	
	private String threadName;
	
	private boolean quite;
	
	public Statistics(String threadName, long startTime, boolean quite) {
		this.startTime = startTime;
		this.threadName = threadName;
		this.quite = quite;
	}
	
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Computing the overall running time for the current thread.
	 * 
	 * @return the time in <b>seconds</b>.
	 */
	public double computeRunningTime() {
		return (endTime - startTime) / 1000;
	}
	
	/**
	 * Compose message for start the thread.
	 * 
	 * @param name the name of the current thread
	 * @return the log message indicationg the start of the new thread.
	 * @throws IOException 
	 */
	public void logStart() throws IOException {
		if (quite) {
			return ;
		}
		StringBuilder sb = new StringBuilder("Thread <");
		sb.append(threadName);
		sb.append("> start at: ");
		sb.append(System.currentTimeMillis());
		sb.append(".");
		sb.append(System.getProperty("line.separator"));
		System.out.print(sb);
	}
	
	/**
	 * Compose message for end the thread.
	 * 
	 * @param name the name of the current thread
	 * @return the log message indicating the end of the old thread.
	 * @throws IOException 
	 */
	public void logEnd() throws IOException {
		if (quite) {
			return ;
		}
		StringBuilder sb = new StringBuilder("Thread <");
		sb.append(threadName);
		sb.append("> ends at: ");
		sb.append(System.currentTimeMillis());
		sb.append(".");
		sb.append(System.getProperty("line.separator"));
		System.out.print(sb);
	}

	/**
	 * Writes a message in a file.
	 * 
	 * @param sb a message to write.
	 * @throws IOException 
	 */
	public static void writeMessage(File file, StringBuilder sb) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(new String(sb));
		bw.close();
	}
}
