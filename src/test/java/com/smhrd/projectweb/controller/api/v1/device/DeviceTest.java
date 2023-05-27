package com.smhrd.projectweb.controller.api.v1.device;


import com.smhrd.projectweb.restdocs.support.AuthTestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.restdocs.request.PathParametersSnippet;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@WebMvcTest(DeviceApi.class)
@PropertySource("classpath:application.properties")
@Slf4j
class DeviceTest extends AuthTestSupport {

    PathParametersSnippet deviceIdPathParameterSnippet = pathParameters(
            parameterWithName("deviceId").description("Device Id to use")
    );

    @Test
    void getAuthenticatedDevice() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/device/get", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("data.id").value(1))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("data.id").description("Id of device"),
                                fieldWithPath("data.dateCreated").description("UTC time of device created"),
                                fieldWithPath("data.lastEdited").description("UTC time of device last edited")
                        )
                ));
    }

    @Test
    void getDevice1() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/device/get/{deviceId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("data.id").value(1))
                .andDo(restDocs.document(
                        deviceIdPathParameterSnippet,
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("data.id").description("Id of device"),
                                fieldWithPath("data.dateCreated").description("UTC time of device created"),
                                fieldWithPath("data.lastEdited").description("UTC time of device last edited")
                        )
                ));
    }

    @Test
    void getInvalidDevice() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/device/get/5")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", getJwt()))
                                .with(authentication(authentication)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("status").value("error"))
                .andExpect(jsonPath("message").value("No device found"))
                .andDo(restDocs.document(
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response")
                        )
                ));
    }
}
