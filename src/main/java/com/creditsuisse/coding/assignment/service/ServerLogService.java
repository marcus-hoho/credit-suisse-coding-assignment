package com.creditsuisse.coding.assignment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditsuisse.coding.assignment.entity.ServerLog;
import com.creditsuisse.coding.assignment.exceptions.ServerLogNotFoundException;
import com.creditsuisse.coding.assignment.repository.ServerLogRepository;

@Service
public class ServerLogService {

	@Autowired
	private ServerLogRepository serverLogRepository;

	private Logger logger=LoggerFactory.getLogger(ServerLogService.class);;

	public List<ServerLog> getAllServerLogs() {
		return serverLogRepository.findAll();
	}
	
	public ServerLog getServerLogById(String id) throws  ServerLogNotFoundException{
		return serverLogRepository.findById(id).orElseThrow(()->new ServerLogNotFoundException(id));
	}
	
	public void saveServerLog(ServerLog serverLog) {
		ServerLog serverLogFromDB=null;
		try {
			serverLogFromDB=getServerLogById(serverLog.getId());
		}catch(ServerLogNotFoundException slnfe) {
			logger.info(slnfe.getMessage());
		}
		if(serverLogFromDB!=null) { // found a serverLog Record from DB
			if(serverLog.getState().equals(ServerLog.STARTED_STATE)) {
				serverLogFromDB.setStartTime(serverLog.getTimestamp());
			}else {
				serverLogFromDB.setEndTime(serverLog.getTimestamp());
			}
			if(serverLogFromDB.getStartTime()!=null && serverLogFromDB.getEndTime()!=null) {
				long duration=serverLogFromDB.getEndTime()-serverLogFromDB.getStartTime();
				serverLogFromDB.setDuration(duration);
				if( duration > 4 ) {
					serverLogFromDB.setAlert(true);
				}
			}
			serverLogRepository.saveAndFlush(serverLogFromDB);
		}else {
			if(serverLog.getState().equals(ServerLog.STARTED_STATE)) {
				serverLog.setStartTime(serverLog.getTimestamp());
			}else {
				serverLog.setEndTime(serverLog.getTimestamp());
			}
			serverLogRepository.saveAndFlush(serverLog);
		}
	}
	
	
}
