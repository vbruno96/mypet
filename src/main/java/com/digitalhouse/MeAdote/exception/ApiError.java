package com.digitalhouse.MeAdote.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiError {
	
	private HttpStatus status;
	private String reason;
	private String message;

}
