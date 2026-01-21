package com.test.MetamorphAssesment.exceptionLayer;

public class ApodNotFoundException extends RuntimeException{

	public ApodNotFoundException(String message) {
        super(message);
    }
}
