package com.freaky.fit.exception;

import com.freaky.fit.utils.StackTraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ExceptionResponse> handleException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        log.error("Exception Logs- {}", StackTraceUtil.getStackTrace(ex));
        if (ex instanceof HttpMessageNotReadableException) {
            return handleException(HttpStatus.UNPROCESSABLE_ENTITY, ex, request);
        } else if (ex instanceof HttpMessageConversionException) {
            return handleException(HttpStatus.BAD_REQUEST, ex, request);
        } else if (ex instanceof BusinessException) {
            if (((BusinessException) ex).isPrintStackTrace()) {
                return handleException(((BusinessException) ex).getErrorStatus(), ex, request);
            }
            return handleException(((BusinessException) ex).getErrorStatus(), ex.getMessage(), request);
        } else {
                log.error("P3|Error Code 500|{}", StackTraceUtil.getStackTrace(ex));
                return handleException(
                        HttpStatus.INTERNAL_SERVER_ERROR, StackTraceUtil.getStackTrace(ex), request);

        }
    }

    protected ResponseEntity<ExceptionResponse> handleException(
            HttpStatus status, Throwable ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                        .httpCodeMessage(status.getReasonPhrase())
                        .details(request.getDescription(false))
                        .message(ex.getMessage())
                        .timestamp(new Date().toString())
                        .traceId(request.getHeader("TraceId"))
                        .build();

        log.error("Runtime Exception Response {}", exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, status);
    }

    protected ResponseEntity<ExceptionResponse> handleException(
            HttpStatus status, String errorMessage, WebRequest request) {
        ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                        .httpCodeMessage(status.getReasonPhrase())
                        .details(request.getDescription(false))
                        .message(errorMessage)
                        .timestamp(new Date().toString())
                        .traceId(request.getHeader("TraceId"))
                        .build();

        log.error("Runtime Exception Response {}", exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
