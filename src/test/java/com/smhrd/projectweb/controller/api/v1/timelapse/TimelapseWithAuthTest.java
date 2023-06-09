package com.smhrd.projectweb.controller.api.v1.timelapse;


import com.smhrd.projectweb.payload.request.api.v1.timelapse.TimelapseRequest;
import com.smhrd.projectweb.restdocs.support.AuthTestSupport;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TimelapseWithAuthApi.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TimelapseWithAuthTest extends AuthTestSupport {

    @Test
    @Order(1)
    void getLatest() throws Exception {
        this.mockMvc.perform(
                        get("/timelapse/latest")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("data.id").value(1))
                .andExpect(jsonPath("data.deviceId").value(1))
                .andExpect(jsonPath("data.status").value("DONE"))
                .andExpect(jsonPath("data.logStartDate").value("2023-05-20T00:00:00.000Z"))
                .andExpect(jsonPath("data.logEndDate").value("2023-05-30T00:00:00.000Z"))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("data.id").description("Id of timelapse request"),
                                fieldWithPath("data.deviceId").description("Device Id of timelapse request"),
                                fieldWithPath("data.status").description("Status of timelapse request. Can be `NOT_STARTED`, `IN_PROGRESS`, or `DONE`."),
                                fieldWithPath("data.result").optional().type(JsonFieldType.STRING).description("Result of timelapse. Can be optional if `status` is not `DONE`"),
                                fieldWithPath("data.logStartDate").description("UTC time of log start date"),
                                fieldWithPath("data.logEndDate").description("UTC time of log end date"),
                                fieldWithPath("data.lastUpdated").description("UTC time of last updated")
                        )
                ));
    }

    @Test
    @Order(2)
    void postRequest() throws Exception {
        TimelapseRequest timelapseRequest = new TimelapseRequest(1L, OffsetDateTime.of(
                2022, 10, 11, 12, 13, 14, 0, ZoneOffset.UTC
        ), OffsetDateTime.of(
                2022, 11, 12, 13, 14, 15, 0, ZoneOffset.UTC
        ));

        this.mockMvc.perform(
                        post("/timelapse/request")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .content(objectMapper.writeValueAsString(timelapseRequest))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("data.deviceId").value(1))
                .andExpect(jsonPath("data.status").value("NOT_STARTED"))
                .andExpect(jsonPath("data.logStartDate").value("2022-10-11T12:13:14.000Z"))
                .andExpect(jsonPath("data.logEndDate").value("2022-11-12T13:14:15.000Z"))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        requestFields(
                                fieldWithPath("deviceId").description("Device Id of timelapse request"),
                                fieldWithPath("startDate").description("UTC time of log start date"),
                                fieldWithPath("endDate").description("UTC time of log end date")
                        ),
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("data.id").description("Id of timelapse request"),
                                fieldWithPath("data.deviceId").description("Device Id of timelapse request"),
                                fieldWithPath("data.status").description("Status of timelapse request. Can be `NOT_STARTED`"),
                                fieldWithPath("data.logStartDate").description("UTC time of log start date"),
                                fieldWithPath("data.logEndDate").description("UTC time of log end date"),
                                fieldWithPath("data.lastUpdated").description("UTC time of last updated")
                        )
                ));
    }

    @Test
    @Order(3)
    void getAll() throws Exception {
        this.mockMvc.perform(
                        get("/timelapse/all")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("data[1].id").value(1))
                .andExpect(jsonPath("data[1].deviceId").value(1))
                .andExpect(jsonPath("data[1].status").value("DONE"))
                .andExpect(jsonPath("data[1].logStartDate").value("2023-05-20T00:00:00.000Z"))
                .andExpect(jsonPath("data[1].logEndDate").value("2023-05-30T00:00:00.000Z"))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("data[].id").description("Id of timelapse request"),
                                fieldWithPath("data[].deviceId").description("Device Id of timelapse request"),
                                fieldWithPath("data[].status").description("Status of timelapse request. Can be `NOT_STARTED`, `IN_PROGRESS`, or `DONE`."),
                                fieldWithPath("data[].result").optional().type(JsonFieldType.STRING).description("Result of timelapse. Can be optional if `status` is not `DONE`"),
                                fieldWithPath("data[].logStartDate").description("UTC time of log start date"),
                                fieldWithPath("data[].logEndDate").description("UTC time of log end date"),
                                fieldWithPath("data[].lastUpdated").description("UTC time of last updated")
                        )
                ));
    }
}
