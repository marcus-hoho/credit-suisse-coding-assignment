package com.creditsuisse.coding.assignment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.creditsuisse.coding.assignment.entity.ServerLog;
import com.creditsuisse.coding.assignment.exceptions.ServerLogNotFoundException;
import com.creditsuisse.coding.assignment.service.ServerLogService;
import com.google.gson.Gson;

@Component
@Scope("prototype")
public class ServerLogThread extends Thread{

	private String serverLogFile;
	private Logger logger=null;
	
	private final ServerLogService serverLogService;
    
	@Autowired
	public ServerLogThread(ServerLogService serverLogService) {
		this.logger = LoggerFactory.getLogger(Application.class);
		this.serverLogService = serverLogService;
	}
	
	@Override
	public void run() {
		logger.info(Thread.currentThread().getName()+" is running with serverLogFile="+this.serverLogFile);
		processServerLogFile();

	}
	
	public void setServerLogFile(String serverLogFile) {
		this.serverLogFile=serverLogFile;
	}
	
	private void processServerLogFile() {
		try {
	        InputStream inputStream = new FileInputStream(this.serverLogFile);
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	        Stream<String> linesStream = bufferedReader.lines();
	        linesStream.forEach(this::processServerLogString);	
		}catch(IOException ioe) {
			ioe.printStackTrace();		
		}
	}
	
	private void processServerLogString(String line) {
		logger.debug("processServerLogString-->"+line);
		ServerLog serverLog = getServerLogObject(line);
		serverLogService.saveServerLog(serverLog);		
	}
	
	private ServerLog getServerLogObject(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, ServerLog.class);		
	}
}
