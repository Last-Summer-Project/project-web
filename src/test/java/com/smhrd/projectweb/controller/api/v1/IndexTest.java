package com.smhrd.projectweb.controller.api.v1;

import com.smhrd.projectweb.restdocs.support.BasicTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Index.class)
class IndexTest extends BasicTestSupport {

    @Test
    void index() throws Exception {
        this.mockMvc.perform(
                get("/api/v1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("안녕, 세계!"))
                .andDo(restDocs.document(
                        responseFields(
                                fieldWithPath("status").description("Status of response"),
                                fieldWithPath("message").description("Hello, World!")
                        )
                ));
    }
}