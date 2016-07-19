package com.rsa.automation.core.test.suites;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rsa.core.Commands;
import com.rsa.core.CommandsHelper;

public class CommandsTest {

	private Options options;

	private CommandLineParser parser;

	@BeforeTest
	public void setup() {
		this.options = CommandsHelper.configureOptions();
		this.parser = new DefaultParser();
	}

	@Test(description="Test the task option without argument.",
			expectedExceptions=ParseException.class)
	public void testTaskOptionFailed1() throws ParseException {
		String[] args = new String[]{Commands.TASKS.getLongName(), "="};
		CommandLine commands = parser.parse(options, args);
		CommandsHelper.parseTasksOption(commands);
	}


	@Test(description="Test the task option with an invalid argument.",
			expectedExceptions=ParseException.class)
	public void testTaskOptionFailed2() throws ParseException {
		String[] args = new String[]{Commands.TASKS.getLongName(), "=", "blq"};
		CommandLine commands = parser.parse(options, args);
		CommandsHelper.parseTasksOption(commands);
	}

	
}
