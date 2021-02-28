package com.bfxy.base.rabbit.rabbitcommon.mybatis.handler;

import com.bfxy.base.rabbit.rabbitapi.Message;
import com.bfxy.base.rabbit.rabbitcommon.util.FastJsonConvertUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.commons.lang3.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kylin Su
 * @date 2021/2/22 1:59 AM
 */
public class MessageJsonTypeHandler extends BaseTypeHandler<Message> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Message parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, FastJsonConvertUtil.convertObjectToJSON(parameter));
    }

    @Override
    public Message getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        if (null != value && !StringUtils.isBlank(value)) {
            return FastJsonConvertUtil.convertJSONToObject(value, Message.class);
        }
        return null;
    }

    @Override
    public Message getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String value = resultSet.getString(columnIndex);
        if(null != value && !StringUtils.isBlank(value)) {
            return FastJsonConvertUtil.convertJSONToObject(value, Message.class);
        }
        return null;
    }

    @Override
    public Message getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String value = callableStatement.getString(columnIndex);
        if(null != value && !StringUtils.isBlank(value)) {
            return FastJsonConvertUtil.convertJSONToObject(
                    callableStatement.getString(columnIndex), Message.class);
        }
        return null;
    }
}
