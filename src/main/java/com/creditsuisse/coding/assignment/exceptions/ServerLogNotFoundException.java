package com.creditsuisse.coding.assignment.exceptions;

public class ServerLogNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerLogNotFoundException(String Id) {
		super("ServerLog [id="+Id+"] not Found!");
	}
}
