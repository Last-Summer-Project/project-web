package com.smhrd.projectweb.controller.api.v1.device;

import com.smhrd.projectweb.entity.request.api.v1.AuthRequest;
import com.smhrd.projectweb.restdocs.support.AuthTestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthApi.class)
@PropertySource("classpath:application.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class AuthTest extends AuthTestSupport {

    @Test
    @Order(1)
    void signup() throws Exception {
        AuthRequest input = new AuthRequest("user", "pass");

        this.mockMvc.perform(
                post("/api/v1/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("message").value("Successfully signed up"))
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("loginId").description("Device login id"),
                                fieldWithPath("password").description("Device login password")),
                        responseFields(fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response"),
                                fieldWithPath("data").description("Json Web Token")
                        )
                ));
    }

    @Test
    @Order(2)
    void login() throws Exception {
        AuthRequest input = new AuthRequest("user", "pass");

        this.mockMvc.perform(
                post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("message").value("Successfully logged in"))
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("loginId").description("Device login id"),
                                fieldWithPath("password").description("Device login password")),
                        responseFields(fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response"),
                                fieldWithPath("data").description("Json Web Token")
                        )
                ));
    }

    @Test
    @Order(3)
    void refresh() throws Exception {
        String jwtValue = deviceUserService.login("user", "pass");

        this.mockMvc.perform(
                get("/api/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", String.format("Bearer %s", jwtValue)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("ok"))
                .andExpect(jsonPath("message").value("Successfully refreshed token"))
                .andDo(restDocs.document(
                        authorizationHeaderSnippet,
                        responseFields(fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of response"),
                                fieldWithPath("data").description("Json Web Token")
                        )
                ));
    }
}
