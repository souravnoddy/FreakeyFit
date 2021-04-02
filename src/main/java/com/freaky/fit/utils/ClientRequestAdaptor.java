package com.freaky.fit.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@Slf4j
public class ClientRequestAdaptor {

  public static final int CONNECT_TIMEOUT = 30000;

  public <T, U> ResponseEntity<T> postApiResponse(
      String baseUrl, U requestBody, Class<T> serviceResponse, Map<String, String> requestHeaders) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate
        .getMessageConverters()
        .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
    requestFactory.setReadTimeout(CONNECT_TIMEOUT);

    restTemplate.setRequestFactory(requestFactory);

    HttpHeaders headers = new HttpHeaders();
    requestHeaders.forEach(headers::set);

    HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<T> response = null;
    try {
      log.info("Calling POST {} ", baseUrl);
      response = restTemplate.postForEntity(baseUrl, requestEntity, serviceResponse);
    } catch (Exception e) {
      return handleException(e, headers);
    }

    return response;
  }

  public <T> ResponseEntity<T> getApiResponse(
      String apiUrl, Class<T> serviceResponse, Map<String, String> requestHeaders) {

    RestTemplate restTemplate = new RestTemplate();

    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
    requestFactory.setReadTimeout(CONNECT_TIMEOUT);

    restTemplate.setRequestFactory(requestFactory);

    HttpHeaders headers = new HttpHeaders();
    requestHeaders.forEach(headers::set);

    HttpEntity<?> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<T> response = null;

    try {
      log.info("Calling GET {} ", apiUrl);
      response = restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, serviceResponse);
    } catch (Exception e) {

      return handleException(e, headers);
    }

    return response;
  }

  public <T, U> ResponseEntity<T> deleteApiResponse(
      String baseUrl, U requestBody, Class<T> serviceResponse, Map<String, String> requestHeaders) {

    RestTemplate restTemplate = new RestTemplate();

    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
    requestFactory.setReadTimeout(CONNECT_TIMEOUT);

    restTemplate.setRequestFactory(requestFactory);
    HttpHeaders headers = new HttpHeaders();
    requestHeaders.forEach(headers::set);

    HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<T> response = null;
    try {
      log.info("Calling DELETE {} ", baseUrl);
      response = restTemplate.exchange(baseUrl, HttpMethod.DELETE, requestEntity, serviceResponse);
    } catch (Exception e) {
      return handleException(e, headers);
    }

    return response;
  }

  public <T, U> ResponseEntity<T> patchApiResponse(
      String baseUrl, U requestBody, Class<T> serviceResponse, Map<String, String> requestHeaders) {

    RestTemplate restTemplate = new RestTemplate();
    restTemplate
        .getMessageConverters()
        .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
    requestFactory.setReadTimeout(CONNECT_TIMEOUT);

    restTemplate.setRequestFactory(requestFactory);
    HttpHeaders headers = new HttpHeaders();
    requestHeaders.forEach(headers::set);

    HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<T> response = null;
    try {
      log.info("Calling PATCH {} ", baseUrl);
      response = restTemplate.exchange(baseUrl, HttpMethod.PATCH, requestEntity, serviceResponse);
    } catch (Exception e) {
      return handleException(e, headers);
    }

    return response;
  }

  private <T> ResponseEntity<T> handleException(Exception e, HttpHeaders httpHeaders) {

    log.error("Error in Http Client Adapter for Trace Id {}", httpHeaders.get("TraceId"), e);

    if (e instanceof HttpClientErrorException.Unauthorized) {
      return new ResponseEntity<T>(HttpStatus.UNAUTHORIZED);
    } else if (e instanceof HttpClientErrorException.Forbidden) {
      return new ResponseEntity<T>(HttpStatus.FORBIDDEN);
    } else if (e instanceof HttpClientErrorException.BadRequest) {
      return new ResponseEntity<T>(HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<T>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
