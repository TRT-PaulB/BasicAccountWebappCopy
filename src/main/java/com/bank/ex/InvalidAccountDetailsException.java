package com.bank.ex;

public class InvalidAccountDetailsException extends RuntimeException {
	public InvalidAccountDetailsException(String msg) {
	    super(msg);
	}
}

