package com.smhrd.projectweb.restdocs.support;

import com.smhrd.projectweb.config.TestSecurityConfig;
import com.smhrd.projectweb.service.device.DeviceUserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;

@Disabled
@Import(TestSecurityConfig.class)
@RequiredArgsConstructor
public class AuthTestSupport extends SqlTestSupport {

    @Autowired
    protected DeviceUserService deviceUserService;

    protected Authentication authentication = new TestingAuthenticationToken("test_user", "pass", "device");
    protected RequestHeadersSnippet authorizationHeaderSnippet = requestHeaders(
            headerWithName(HttpHeaders.AUTHORIZATION).description("Json Web Token with 'Bearer'")
    );

    String testJwtValue;

    protected String getJwt() {
        if (testJwtValue == null) {
            testJwtValue = deviceUserService.login("test_user", "pass");
        }
        return testJwtValue;
    }
}
