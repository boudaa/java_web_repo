package com.boudaa.nerapplication.nlpservices;

public class NerException extends RuntimeException {

	public NerException(String message, Throwable cause) {
		super(message, cause);
	}

	public NerException(String message) {
		super(message);
	}

}
