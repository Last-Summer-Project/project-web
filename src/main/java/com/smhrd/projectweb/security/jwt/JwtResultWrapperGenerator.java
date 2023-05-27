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

    public void generate() throws IOException {
        if (status >= 400 && status <=499) {
            generate(ResultWrapper.fail(message));
            return;
        }

        if (status >= 500 && status <=599) {
            generate(ResultWrapper.error(message));
            return;
        }

        generate(ResultWrapper.of(status, "unknown", message, null));
    }

    private void generate(ResultWrapper<?> obj) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        String json = objectMapper.writeValueAsString(obj);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    }
}