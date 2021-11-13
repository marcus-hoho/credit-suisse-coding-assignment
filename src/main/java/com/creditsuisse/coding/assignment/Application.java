package com.creditsuisse.coding.assignment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.creditsuisse.coding.assignment.config.Config;
import com.creditsuisse.coding.assignment.entity.ServerLog;
import com.creditsuisse.coding.assignment.exceptions.ServerLogNotFoundException;
import com.creditsuisse.coding.assignment.service.ServerLogService;

@Configuration
public class Application {

	private static ApplicationContext context=null;
	private static Logger logger = null;
	private static boolean runUnitTesting=true;
	
	public static void main(String[] args) throws InterruptedException{

	    logger = LoggerFactory.getLogger(Application.class);
	    logger.info("SeverLog Application is running...............");

		if(args!=null ) {
			if(args.length==1) {
			    logger.info("The logfile.txt :"+args[0]);							
			}
			if(args.length==2) {
			    logger.info("runUnitTesting :"+args[1]);	
			    runUnitTesting=Boolean.valueOf(args[1]);
			}
		}else {
			logger.error("Usage : java Application logfilePath runUnitTesting");
			logger.error("e.g.: java Application ./target/classes/logfile.txt true/false");
			System.exit(0);
		}

        context = new AnnotationConfigApplicationContext(Config.class);
		
		ServerLogThread serverLogThread = context.getBean(ServerLogThread.class);
		serverLogThread.setServerLogFile(args[0]);
		serverLogThread.start();
		serverLogThread.join();
		
		if(runUnitTesting) {
			runUnitTesting();
		}
		((ConfigurableApplicationContext) context).close();
	}
	
	public static void runUnitTesting() {

		ServerLogService serverLogService = context.getBean(ServerLogService.class);
		List<ServerLog> serverLogDbs = serverLogService.getAllServerLogs();
		System.out.println("******  ******  All Server Log  ******  ******");
		System.out.println("---------------------------****-----------------------------------");
		serverLogDbs.forEach(pt -> System.out.println(pt));
		System.out.println("---------------------------****----------------------------------");
		try {
			ServerLog serverLogFromDB=serverLogService.getServerLogById("scsmbstgrc");
			System.out.println("---------------------------*scsmbstgrc**-----------------------------------");
			System.out.println(serverLogFromDB);
			serverLogFromDB=serverLogService.getServerLogById("scsmbstgrXX");
		}catch(ServerLogNotFoundException slnfe) {
			logger.info(slnfe.getMessage());
		}	
	}
	

}