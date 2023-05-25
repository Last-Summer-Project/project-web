package com.smhrd.projectweb.controller.api.v1.log;

import com.smhrd.projectweb.restdocs.support.AuthTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LogWithAuthApi.class)
@PropertySource("classpath:application.properties")
class LogWithAuthTest extends AuthTestSupport {

    @Test
    void getLatestLog() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/log/latest")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                // Basic
                .andExpect(jsonPath("data.deviceId").value(1))
                .andExpect(jsonPath("data.humidity").value(35.55D))
                .andExpect(jsonPath("data.temperature").value(21.01D))
                .andExpect(jsonPath("data.soilHumidity").value(0.0))
                // Image
                .andExpect(jsonPath("data.imageUrl").value("/static/image/2.jpg"))
                // Detection
                .andExpect(jsonPath("data.detection.status").value("IN_PROGRESS"))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                // Basic
                                fieldWithPath("data.deviceId").description("Device id of this log"),
                                fieldWithPath("data.humidity").description("Logged relative humidity"),
                                fieldWithPath("data.temperature").description("Logged celsius temperature"),
                                fieldWithPath("data.soilHumidity").description("Logged soil humidity"),
                                // Image
                                fieldWithPath("data.imageUrl").description("Relative image url of logged image"),
                                // Detection
                                fieldWithPath("data.detection.status").description("Status of detection process. can be `NOT_STARTED`, `IN_PROGRESS`, or `DONE`"),
                                fieldWithPath("data.detection.result").type(JsonFieldType.STRING).optional().description("Detected result. Optional if status is not `DONE`"),
                                // Timestamp
                                fieldWithPath("data.timestamp").description("UTC time of log written timestamp")
                        )
                ));
    }

    @Test
    void getLatestDetectedLog() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/log/latest-detected")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                // Basic
                .andExpect(jsonPath("data.deviceId").value(1))
                .andExpect(jsonPath("data.humidity").value(30.0D))
                .andExpect(jsonPath("data.temperature").value(20.09D))
                .andExpect(jsonPath("data.soilHumidity").value(0.0))
                // Image
                .andExpect(jsonPath("data.imageUrl").value("/static/image/1.jpg"))
                // Detection
                .andExpect(jsonPath("data.detection.status").value("DONE"))
                .andExpect(jsonPath("data.detection.result").value(2))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                // Basic
                                fieldWithPath("data.deviceId").description("Device id of this log"),
                                fieldWithPath("data.humidity").description("Logged relative humidity"),
                                fieldWithPath("data.temperature").description("Logged celsius temperature"),
                                fieldWithPath("data.soilHumidity").description("Logged soil humidity"),
                                // Image
                                fieldWithPath("data.imageUrl").description("Relative image url of logged image"),
                                // Detection
                                fieldWithPath("data.detection.status").description("Status of detection process. It should be `DONE`"),
                                fieldWithPath("data.detection.result").description("Detected result"),
                                // Timestamp
                                fieldWithPath("data.timestamp").description("UTC time of log written timestamp")
                        )
                ));
    }

    @Test
    void getRecentLogs() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/log/recent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                // Basic
                .andExpect(jsonPath("data[1].deviceId").value(1))
                .andExpect(jsonPath("data[1].humidity").value(30.0D))
                .andExpect(jsonPath("data[1].temperature").value(20.09D))
                .andExpect(jsonPath("data[1].soilHumidity").value(0.0))
                // Image
                .andExpect(jsonPath("data[1].imageUrl").value("/static/image/1.jpg"))
                // Detection
                .andExpect(jsonPath("data[1].detection.status").value("DONE"))
                .andExpect(jsonPath("data[1].detection.result").value(2))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                // Basic
                                fieldWithPath("data[].deviceId").description("Device id of this log"),
                                fieldWithPath("data[].humidity").description("Logged relative humidity"),
                                fieldWithPath("data[].temperature").description("Logged celsius temperature"),
                                fieldWithPath("data[].soilHumidity").description("Logged soil humidity"),
                                // Image
                                fieldWithPath("data[].imageUrl").description("Relative image url of logged image"),
                                // Detection
                                fieldWithPath("data[].detection.status").description("Status of detection process. can be `NOT_STARTED`, `IN_PROGRESS`, or `DONE`"),
                                fieldWithPath("data[].detection.result").type(JsonFieldType.STRING).optional().description("Detected result. Optional if status is not `DONE`"),
                                // Timestamp
                                fieldWithPath("data[].timestamp").description("UTC time of log written timestamp")
                        )
                ));
    }
}
