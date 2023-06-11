package com.smhrd.projectweb.controller.detect;

import com.smhrd.projectweb.entity.Status;
import com.smhrd.projectweb.payload.request.detect.DetectRequest;
import com.smhrd.projectweb.payload.response.detect.DetectResponse;
import com.smhrd.projectweb.restdocs.support.AuthTestSupport;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DetectApi.class)
@Slf4j
public class DetectTest extends AuthTestSupport {

    public static MockWebServer mockBackEnd;

    private String readTestImageBase64() throws Exception {
        InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream("test-image.jpg");
        assert ioStream != null;
        byte[] data = ioStream.readAllBytes();
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(data);
    }

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start(5000);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void postDetectRequest() throws Exception {
        String file = readTestImageBase64();
        DetectRequest detectRequest = new DetectRequest(file);
        DetectResponse mockDetectResponse = new DetectResponse(Status.DONE, "0");
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(mockDetectResponse))
                .addHeader("Content-Type", "application/json"));

        this.mockMvc.perform(
                        post("/detect/request")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication))
                                .content(objectMapper.writeValueAsString(detectRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("data.status").value("DONE"))
                .andExpect(jsonPath("data.result").value(0))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        requestFields(
                                fieldWithPath("imageBase64").description("Data uri of request image")
                        ),
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("data.status").description("Status of detection process. It should be `DONE`"),
                                fieldWithPath("data.result").description("Detected result")
                        )
                ));
    }
}
