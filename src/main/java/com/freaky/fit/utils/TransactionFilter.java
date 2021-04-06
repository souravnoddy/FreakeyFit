package com.freaky.fit.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(value = 1)
@Slf4j
public class TransactionFilter extends OncePerRequestFilter {

    public static final String TRACE_ID = "traceId";
    public static final String AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            if (request.getHeader(TRACE_ID) != null && request.getHeader(TRACE_ID).length() > 0) {
                MDC.put(TRACE_ID, request.getHeader(TRACE_ID));
            } else {
                MDC.put(TRACE_ID, UUID.randomUUID().toString());
            }
            String authorization = request.getHeader(AUTHORIZATION);
            if (null != authorization && authorization.contains("Bearer")) {
                MDC.put(AUTHORIZATION, authorization);
            } else {
                MDC.put(AUTHORIZATION, "Bearer " + authorization);
                log.warn("AUTHORIZATION token not found... Moving ahead..");
            }

        } catch (Exception ex) {
            log.error("Exception in transaction filter", ex);
        }

        filterChain.doFilter(request, response);
    }
}
