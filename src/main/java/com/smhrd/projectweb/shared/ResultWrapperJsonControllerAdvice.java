package com.smhrd.projectweb.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 만약 Jackson Json 파싱 전 bodyContainer 값이 {@link ResultWrapper}이면
 * {@link ResultWrapper#httpStatusCode}를 가져와서 해당 status code를 설정하는 코드입니다.
 */
@RestControllerAdvice
@Slf4j
public class ResultWrapperJsonControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {
    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            ResultWrapper<?> rw = (ResultWrapper<?>) bodyContainer.getValue();
            int statusCode = rw.getHttpStatusCode();
            log.error(String.valueOf(statusCode));
            if (statusCode != 0) {
                HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
                resp.setStatus(statusCode);
            }
        } catch (Exception e) {
            // It's not ResultWrapper so ignore.
        }
    }
}
