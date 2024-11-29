package com.ryuqq.setof.api.core.filter;

import com.ryuqq.setof.domain.core.TraceIdHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    public static final String HEALTH_URI = "/health";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        String traceId = UUID.randomUUID().toString();
        TraceIdHolder.setTraceId(traceId);

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");

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
            String queryString = request.getQueryString();
            String uri = request.getRequestURI() + (queryString != null ? "?" + queryString : "");

            log.info("Request: traceId ={}, method={}, uri={}, headers={}, body={}, clientIp={}, userAgent={}",
                    traceId,
                    request.getMethod(),
                    uri,
                    getHeaders(request),
                    getRequestBody(request),
                    clientIp,
                    userAgent
            );
        } catch (Exception e) {
            log.error("Unexpected error during request logging", e);
        }
    }


    private void logResponse(ContentCachingResponseWrapper response, long duration) {
        log.info("[{}, duration={}ms]", logPayload(response.getContentType(), response.getContentAsByteArray()), duration);
    }



    private String getRequestBody(RequestWrapper request) throws IOException {
        String contentType = request.getContentType();
        boolean visible = isVisible(MediaType.valueOf(contentType == null ? "application/json" : contentType));
        if (visible) {
            byte[] content = request.getContentAsByteArray();
            if (content.length > 0) {
                return new String(content, request.getCharacterEncoding() != null ? request.getCharacterEncoding() : StandardCharsets.UTF_8.name());
            }
        }
        return "";
    }


    private String logPayload(String contentType, byte[] content) {
        try {
            boolean visible = isVisible(MediaType.valueOf(contentType == null ? "application/json" : contentType));
            if (visible && content.length > 0) {
                String charset = StandardCharsets.UTF_8.name(); // UTF-8 고정
                return new String(content, charset);
            }
        } catch (Exception e) {
            log.error("Failed to log payload", e);
        }
        return "";
    }

    private boolean isVisible(MediaType mediaType) {
        final List<MediaType> VISIBLE_TYPES = Arrays.asList(
                MediaType.valueOf("text/*"),
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.valueOf("application/*+json"),
                MediaType.valueOf("application/*+xml"),
                MediaType.MULTIPART_FORM_DATA
        );

        return VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
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
