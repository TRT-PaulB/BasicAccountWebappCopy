package com.bank.ex;

public class NotImplementedException extends RuntimeException {
	public NotImplementedException(String missingImpl) {
	    super("Path not yet implemented for " + missingImpl);
	}
}
