package com.ryuqq.setof.api.core.filter;

import com.ryuqq.setof.domain.core.TraceIdHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    public static final String HEALTH_URI = "/health";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        String traceId = UUID.randomUUID().toString();
        TraceIdHolder.setTraceId(traceId); // traceId 저장


        RequestWrapper requestWrapper = new RequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(requestWrapper, wrappedResponse);

        }finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequest(requestWrapper);
            logResponse(wrappedResponse, duration);

            wrappedResponse.copyBodyToResponse();
            TraceIdHolder.clear();

        }
    }

    private void logRequest(RequestWrapper request) {
        try {
            String userAgent = request.getHeader("User-Agent");
            String clientIp = getClientIp(request);
            String traceId = TraceIdHolder.getTraceId();
            String body = new String(request.getContentAsByteArray());
            log.info("Request: traceId ={}, method={}, uri={}, headers={}, body={}, clientIp={}, userAgent={}",
                    traceId,
                    request.getMethod(),
                    request.getRequestURI(),
                    getHeaders(request),
                    body,
                    clientIp,
                    userAgent
            );
        } catch (Exception e) {
            log.error("Unexpected error during request logging", e);
        }
    }

    private void logResponse(ContentCachingResponseWrapper response, long duration) {
        try {
            String traceId = TraceIdHolder.getTraceId();
            log.info("Response: traceId={}, status={}, headers={}, body={}, duration={}ms",
                    traceId,
                    response.getStatus(),
                    response.getHeaderNames(),
                    new String(response.getContentAsByteArray()),
                    duration);
        } catch (Exception e) {
            log.error("Failed to log response", e);
        }
    }

    private String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                headers.append(headerName).append("=").append(request.getHeader(headerName)).append(", ")
        );
        return headers.toString();
    }

    private String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }


}
