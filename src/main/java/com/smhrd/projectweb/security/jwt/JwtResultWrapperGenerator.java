package com.smhrd.projectweb.security.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd.projectweb.shared.ResultWrapper;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
class JwtResultWrapperGenerator {
    private final int status;
    private final String message;
    private final ObjectMapper objectMapper;
    private final HttpServletResponse response;

    public void fail() throws IOException {
        generate(ResultWrapper.fail(message));
    }

    public void error() throws IOException {
        generate(ResultWrapper.error(message));
    }

    private void generate(ResultWrapper<?> obj) throws IOException {
        response.setStatus(status);
        String json = objectMapper.writeValueAsString(obj);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    }
}