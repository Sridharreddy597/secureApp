package com.example.secureApp.Handler;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@ExceptionHandler(value =Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
		final String referencedId = UUID.randomUUID().toString();
		final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		logException(status, referencedId, request, ex);
		return new ResponseEntity<>("{\"referenceId\":" + referencedId + ",\"message\":\"unhandled error\"}", status);
	}
	
	@ExceptionHandler(value=AccessDeniedException.class)
	protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
		final String referencedId = UUID.randomUUID().toString();
		final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		logException(status, referencedId, request, ex);
		return new ResponseEntity<>("{\"referenceId\":" + referencedId + ",\"message\":\"Unauthorised Access \"}", status);
	}
	
	@ExceptionHandler(value=AuthenticationCredentialsNotFoundException.class)
	protected ResponseEntity<Object> handleAuthenticationCredentialsNotFound(AccessDeniedException ex, HttpServletRequest request) {
		final String referencedId = UUID.randomUUID().toString();
		final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		logException(status, referencedId, request, ex);
		return new ResponseEntity<>("{\"referenceId\":" + referencedId + ",\"message\":\"Unauthorised Access\"}", status);
	}

	private void logException(final HttpStatus httpStatus, final String referencedId, final HttpServletRequest request,
			Exception ex) {
		String error = "Context : httpStatus= {" + httpStatus + "} responseReferenceId={" + referencedId
				+ "} exceptionType={" + ex.getClass() + "} " + "remoteAddr={" + request.getRemoteAddr()
				+ "} requestUrl={" + request.getRequestURL() + "}";
		logger.error(error, ex);
		logger.error("stack-trace: responseReferenceId={" + referencedId + "}", ex);
	}
}
