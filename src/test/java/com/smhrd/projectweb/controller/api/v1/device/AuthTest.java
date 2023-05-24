package com.smhrd.projectweb.controller.api.v1.device;

import com.smhrd.projectweb.restdocs.RestDocsTestSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthApi.class)
@PropertySource("classpath:application.properties")
@Slf4j
public class AuthTest extends RestDocsTestSupport {

    @BeforeAll
    public static void init(@Autowired SqlSessionFactory sqlSessionFactory) {
        try (Connection connection = sqlSessionFactory.openSession().getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("table-template.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void signup() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("loginId", "user");
        input.put("password", "pass");

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
                                fieldWithPath("password").description("Device login password")
                        ),
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Message of request"),
                                fieldWithPath("data").description("JWT Token")
                        )
                )
        );
    }



}
