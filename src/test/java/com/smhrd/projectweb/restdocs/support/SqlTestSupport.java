package com.smhrd.projectweb.restdocs.support;

import com.smhrd.projectweb.service.device.DeviceUserService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Connection;

@Disabled
@RequiredArgsConstructor
public class SqlTestSupport extends BasicTestSupport {

    @Autowired
    protected SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void setupSql(@Autowired SqlSessionFactory sqlSessionFactory) {
        try (Connection connection = sqlSessionFactory.openSession().getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("table-template.sql"));
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("fake-value.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
