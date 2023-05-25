package com.smhrd.projectweb.controller.api.v1.device;


import com.smhrd.projectweb.config.TestSecurityConfig;
import com.smhrd.projectweb.restdocs.RestDocsTestSupportWithSql;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;


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
@Import(TestSecurityConfig.class)
class DeviceTest extends RestDocsTestSupportWithSql {

    Authentication authentication = new TestingAuthenticationToken("test_user", "pass", "device");
    String testJwtValue;


    private String getJwt() {
        if (testJwtValue == null) {
            testJwtValue = deviceUserService.login("test_user", "pass");
        }
        return testJwtValue;
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
                        pathParameters(
                                parameterWithName("deviceId").description("Device Id to get")
                        ),
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