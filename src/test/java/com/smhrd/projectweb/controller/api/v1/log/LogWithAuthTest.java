package com.smhrd.projectweb.controller.api.v1.log;

import com.smhrd.projectweb.payload.request.api.v1.v1.log.LogWriteRequest;
import com.smhrd.projectweb.restdocs.support.AuthTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LogWithAuthApi.class)
@PropertySource("classpath:application.properties")
class LogWithAuthTest extends AuthTestSupport {

    String base64OnePixelJpeg = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/2wBDAQICAgMDAwYDAwYMCAcIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAABAAEDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9/KKKKAP/2Q==";

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
                                fieldWithPath("data.deviceId").description("Device ID of this log. Should be equal to JWT's Device id."),
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
                                fieldWithPath("data.deviceId").description("Device ID of this log"),
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
                                fieldWithPath("data[].deviceId").description("Device ID of this log."),
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

    @Test
    void postWriteLog() throws Exception {
        LogWriteRequest input = new LogWriteRequest();
        input.setDeviceId(1L);
        input.setRelativeHumidity(12.34);
        input.setTemperature(19.87);
        input.setSoilHumidity(0.0);
        input.setImageBase64(base64OnePixelJpeg);

        this.mockMvc.perform(
                        post("/api/v1/log/write")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .content(objectMapper.writeValueAsString(input))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                // Basic
                .andExpect(jsonPath("data.deviceId").value(1))
                .andExpect(jsonPath("data.humidity").value(12.34D))
                .andExpect(jsonPath("data.temperature").value(19.87D))
                .andExpect(jsonPath("data.soilHumidity").value(0.0))
                // Detection
                .andExpect(jsonPath("data.detection.status").value("NOT_STARTED"))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        requestFields(
                                fieldWithPath("deviceId").description("Device ID of log. Should be equal to JWT's Device ID."),
                                fieldWithPath("relativeHumidity").description("Relative humidity of log."),
                                fieldWithPath("temperature").description("Temperature (celsius) of log."),
                                fieldWithPath("soilHumidity").description("Soil humidity of log."),
                                fieldWithPath("imageBase64").description("Base64 encoded image data uri of log.")
                        ),
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                // Basic
                                fieldWithPath("data.deviceId").description("Device id of this log."),
                                fieldWithPath("data.humidity").description("Logged relative humidity."),
                                fieldWithPath("data.temperature").description("Logged celsius temperature."),
                                fieldWithPath("data.soilHumidity").description("Logged soil humidity,"),
                                // Image
                                fieldWithPath("data.imageUrl").description("Relative image url of logged image,"),
                                // Detection
                                fieldWithPath("data.detection.status").description("Status of detection process. It should be `NOT_STARTED`,"),
                                // Timestamp
                                fieldWithPath("data.timestamp").description("UTC time of log written timestamp.")
                        )
                ));
    }
}
