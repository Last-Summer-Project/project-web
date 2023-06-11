package com.smhrd.projectweb.service.detect;

import com.smhrd.projectweb.payload.request.detect.DetectRequest;
import com.smhrd.projectweb.payload.response.detect.DetectResponse;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DetectService {
    private final WebClient webClient;

    public DetectService(@Value("${service.detect.detectServerBaseUrl}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public ResultWrapper<DetectResponse> request(DetectRequest detectRequest) {
        ResponseEntity<DetectResponse> response = webClient
                .post()
                .uri("/predict")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(detectRequest), DetectRequest.class)
                .retrieve()
                .toEntity(DetectResponse.class)
                .block();

        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            log.error(String.format("Got upstream server error: %s", response));
            return ResultWrapper.error("Internal Server Error");
        }
        return ResultWrapper.ok(response.getBody());
    }
}