package com.lionphago.backend.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject; // 必须引入这个类

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ListJsonTypeHandler extends BaseTypeHandler<List<String>> {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Java -> Database (PostgreSQL 特有逻辑)
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        try {
            String jsonString = mapper.writeValueAsString(parameter);

            // 关键点：创建 PGobject 对象
            PGobject jsonObject = new PGobject();
            // 如果你的数据库字段是 jsonb，这里写 "jsonb"；如果是 json，写 "json"
            jsonObject.setType("jsonb");
            jsonObject.setValue(jsonString);

            // 使用 setObject 传入
            ps.setObject(i, jsonObject);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化 List<String> 失败", e);
        }
    }

    /**
     * Database -> Java
     * 读取时，Postgres 驱动通常能把 json/jsonb 列直接作为 String 读出，
     * 所以这里通常不需要改动，用 getString 即可。
     */
    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseJson(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJson(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJson(cs.getString(columnIndex));
    }

    private List<String> parseJson(String content) {
        if (content == null || content.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return mapper.readValue(content, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("反序列化 JSON 失败: " + content, e);
        }
    }
}