package com.smhrd.projectweb.config.mybatis;

import com.smhrd.projectweb.entity.sql.Status;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusEnumTypeHandler  extends BaseTypeHandler<Status> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name().toUpperCase());
    }

    @Override
    public Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Status.valueOf(rs.getString(columnName).toUpperCase());
    }

    @Override
    public Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Status.valueOf(rs.getString(columnIndex).toUpperCase());
    }

    @Override
    public Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Status.valueOf(cs.getString(columnIndex).toUpperCase());
    }
}
