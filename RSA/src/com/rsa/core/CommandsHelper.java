package com.rsa.core;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandsHelper {

	public static Options options = new Options();

	public static Options configureOptions() {	
		Option taskOption = Option.builder(Commands.TASKS.getShortName())
				.longOpt(Commands.TASKS.getLongName())
				.required(true)
				.desc("The number of tasks (threads).")
				.type(Number.class)
				.hasArg()
				.build();
		Option precisionOption = Option.builder(Commands.PRECISION.getShortName())
				.longOpt(Commands.PRECISION.getLongName())
				.required(true)
				.desc("The number of members.")
				.type(Number.class)
				.hasArg()
				.build();
		Option outputFileOption = Option.builder(Commands.OUTPUT.getShortName())
				.longOpt(Commands.OUTPUT.getLongName())
				.required(false)
				.type(String.class)
				.hasArg()
				.build();
		Option quiteModeOption = Option.builder(Commands.QUITE.getShortName())
				.longOpt(Commands.QUITE.getLongName())
				.required(false)
				.desc("Whenever the program should runs in quite mode.")
				.build();
		Option helpOption = Option.builder(Commands.HELP.getShortName())
				.longOpt(Commands.HELP.getLongName())
				.required(false)
				.build();
		
		options.addOption(taskOption);
		options.addOption(precisionOption);
		options.addOption(outputFileOption);
		options.addOption(quiteModeOption);
		options.addOption(helpOption);
		
		return options;
	}
	
	public static void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		String header = "Welcome to Ramanujan's PI computation program. \n";
		String footer = "Author: Hristo G. Todorov, 2016, FMI, Sofia.\n";
		formatter.printHelp("myapp", header, options, footer, true);
	}
	
	public static int parsePrecisionOption(CommandLine commands) throws ParseException {
		int precision = ((Number)commands.getParsedOptionValue(
				Commands.PRECISION.getShortName())).intValue();
		if (precision >= 0) {
			return precision;
		} else {
			throw new ParseException("");
		}
	}
	
	public static int parseTasksOption(CommandLine commands) throws ParseException {
		int tasks = ((Number)commands.getParsedOptionValue(
				Commands.TASKS.getShortName())).intValue();
		if (tasks > 0) {
			return tasks;
		} else {
			throw new ParseException("");
		}
	}
	
}
