package com.example.SparkDesk.exception;

public class AssetNotFoundException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssetNotFoundException(String message) {
        super(message);
    }
}
