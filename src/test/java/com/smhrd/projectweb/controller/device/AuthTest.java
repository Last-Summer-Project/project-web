package com.smhrd.projectweb.controller.device;

import com.smhrd.projectweb.payload.request.api.v1.auth.AuthRequest;
import com.smhrd.projectweb.payload.request.api.v1.auth.RefreshRequest;
import com.smhrd.projectweb.payload.response.api.v1.device.DeviceAuthResponse;
import com.smhrd.projectweb.restdocs.support.AuthTestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthApi.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class AuthTest extends AuthTestSupport {

    @Test
    @Order(1)
    void signup() throws Exception {
        AuthRequest input = new AuthRequest("user", "pass");

        this.mockMvc.perform(
                        post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("message").value("Successfully signed up"))
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("loginId").description("Device login id"),
                                fieldWithPath("password").description("Device login password")),
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response"),
                                fieldWithPath("data.access").description("Access Json Web Token"),
                                fieldWithPath("data.refresh").description("Refresh Json Web Token")
                        )
                ));
    }

    @Test
    @Order(2)
    void login() throws Exception {
        AuthRequest input = new AuthRequest("user", "pass");

        this.mockMvc.perform(
                        post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("message").value("Successfully logged in"))
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("loginId").description("Device login id"),
                                fieldWithPath("password").description("Device login password")),
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response"),
                                fieldWithPath("data.access").description("Access Json Web Token"),
                                fieldWithPath("data.refresh").description("Refresh Json Web Token")
                        )
                ));
    }
    @Test
    @Order(2)
    void loginFailed() throws Exception {
        AuthRequest input = new AuthRequest("user1", "pass2");

        this.mockMvc.perform(
                        post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("status").value("fail"))
                .andExpect(jsonPath("message").value("Failed to login"))
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("loginId").description("Device login id"),
                                fieldWithPath("password").description("Device login password")),
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response")
                        )
                ));
    }

    @Test
    @Order(3)
    void refresh() throws Exception {
        DeviceAuthResponse jwt = deviceUserService.login("user", "pass");
        RefreshRequest input = new RefreshRequest(jwt.getRefresh());

        this.mockMvc.perform(
                        post("/auth/refresh")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("message").value("Successfully refreshed token"))
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("refresh").description("Refresh Json Web Token")
                        ),
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response"),
                                fieldWithPath("data.access").description("Refreshed Access Json Web Token"),
                                fieldWithPath("data.refresh").description("Refreshed Refresh Json Web Token")
                        )
                ));
    }

    @Test
    @Order(4)
    void verify() throws Exception {
        String jwtValue = deviceUserService.login("user", "pass").getAccess();

        this.mockMvc.perform(
                        get("/auth/verify")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", jwtValue)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response")
                        )
                ));
    }

    @Test
    @Order(5)
    void verifyInvalid() throws Exception {
        // Wrong old jwt value
        String jwtValue = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWQiOjIsImlhdCI6MTY4NTAyMzIwNywiZXhwIjoxNjg1MDI2ODA3fQ.9pmOyK4BCb2kk2SzfcIQphcBDNBxN4L4C1AqrPRgqMMDsjZUyTfF7GZcXbfBHqulCgAwp2J41zz7iNu1ZD6p8Q";

        this.mockMvc.perform(
                        get("/auth/verify")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", String.format("Bearer %s", jwtValue)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("status").value("fail"))
                .andExpect(jsonPath("message").value("Unauthorized"))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response")
                        )
                ));
    }
}
