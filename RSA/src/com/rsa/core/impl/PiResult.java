package com.rsa.core.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.rsa.core.Commands;
import com.rsa.core.CommandsHelper;
import com.rsa.core.CommonConstants;
import com.rsa.core.Statistics;

public class PiResult {
	public static void main(String[] args) throws 
	InterruptedException, ExecutionException, IOException, ParseException {

		Options options = CommandsHelper.configureOptions();
		
		CommandLineParser parser = new DefaultParser();
		CommandLine commands = parser.parse(options, args);
		if (commands.hasOption(Commands.HELP.getShortName())) {
			CommandsHelper.printHelp();
			System.exit(0);
		}

		int precision = 1;
		int tasks = 1;
		try {
			precision = CommandsHelper.parsePrecisionOption(commands);
			tasks = CommandsHelper.parseTasksOption(commands);
		} catch (ParseException ex) {
			CommandsHelper.printHelp();
			System.exit(0);
		}

		int end = 0;
		int residual = precision % tasks;
		int batch = precision / tasks;
		boolean isEnd = false;
		boolean isQueit = false;
		File file = null;
		
		if (commands.hasOption(Commands.QUITE.getShortName())) {
			isQueit = true;
		}
		
		if (commands.hasOption(Commands.OUTPUT.getShortName())) {
			file = new File(commands.getOptionValue(Commands.OUTPUT.getShortName()));
		} else {
			file = new File(CommonConstants.DEFAULT_OUTPUT_FILE);
		}

		//limit the number of actual threads
		int poolSize = tasks;
		ExecutorService service = Executors.newFixedThreadPool(poolSize);
		List<CompletableFuture<Void>> futures = new ArrayList<CompletableFuture<Void>>();
		List<RamanujanPi> threads = new ArrayList<>();
		
		long startTime = System.currentTimeMillis();
		for (int n = 0; n < tasks; n++) {
			isEnd |= n + 1 == tasks;
			int finalResidual = isEnd ? residual : 0;
			RamanujanPi x = new RamanujanPi(end, end+=batch+finalResidual, isQueit);
			CompletableFuture<Void> futureTask = CompletableFuture.runAsync(x, service);
			futures.add(futureTask);
			threads.add(x);
		}

		// wait for all tasks to complete before continuing
		CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[futures.size()]));
		long endTime = System.currentTimeMillis();
		
		//Collect the result
		List<BigDecimal> results = threads.stream().parallel().map(
				x -> x.getResult()).collect(Collectors.toList());
		BigDecimal result = new BigDecimal(0);
		for (Iterator<BigDecimal> iterator = results.iterator(); iterator.hasNext();) {
			result = result.add(iterator.next());
		}
		result = result.multiply(CommonConstants.FINAL_RESIDUAL);
		result = BigDecimal.valueOf(4).divide(result, 
				CommonConstants.PRECISION, CommonConstants.MODE);

		StringBuilder sb = new StringBuilder();
		sb.append("The sum is: ");
		sb.append(result);
		if (isQueit) {
			System.out.println("Total time of running: " + (endTime - startTime)/1000 + " seconds.");
		} else {
			Statistics.writeMessage(file, sb);
		}

		//shut down the executor service so that this thread can exit
		service.shutdownNow();
		System.exit(0);
	}
}
