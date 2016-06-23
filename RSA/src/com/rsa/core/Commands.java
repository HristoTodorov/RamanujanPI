package com.rsa.core;

public enum Commands {
	TASKS("t", "tasks"),
	PRECISION("p", "precision"),
	OUTPUT("o", "output"),
	QUITE("q", "quite"),
	HELP("h", "help");
	
	private String shortName;
	
	private String longName;
	
	Commands(String shortName, String longName) {
		this.shortName = shortName;
		this.longName = longName;
	}
	
	public String getLongName() {
		return longName;
	}
	
	public String getShortName() {
		return shortName;
	}
}
