package app.common.util.database;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
public class JdbcHelper implements Closeable {

    private final Connection con;

    public JdbcHelper(DataSource dataSource) {
        try {
            this.con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("failed to get connection.", e);
        }
    }

    public Optional<String> selectString(String sql, Object... parameters) {
        return this.selectSingle(
            sql,
            Optional::empty,
            (rs) -> Optional.of(rs.getString(1)),
            parameters
        );
    }

    public List<String> selectStringList(String sql, Object... parameters) {
        return this.selectList(sql, rs -> rs.getString(1), parameters);
    }

    public int selectInt(String sql, Object... parameters) {
        return this.selectSingle(
            sql,
            () -> 0,
            (rs) -> rs.getInt(1),
            parameters
        );
    }

    public List<Integer> selectIntList(String sql, Object... parameters) {
        return this.selectList(sql, rs -> rs.getInt(1), parameters);
    }

    public <T> Optional<T> select(String sql, ResultSetConverter<T> converter, Object... parameters) {
        return this.selectSingle(
            sql,
            Optional::empty,
            (rs) -> Optional.of(converter.convert(rs)),
            parameters
        );
    }

    public Optional<Map<String, Object>> selectMap(String sql, Object... parameters) {
        return this.selectSingle(sql, Optional::empty, (rs) -> Optional.of(this.convertToMap(rs)), parameters);
    }

    public List<Map<String, Object>> selectMapList(String sql, Object... parameters) {
        return this.selectList(sql, this::convertToMap, parameters);
    }

    private Map<String, Object> convertToMap(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Map<String, Object> map = new HashMap<>();

        int columnCount = metaData.getColumnCount();

        for (int i=1; i<=columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            map.put(columnName, rs.getObject(columnName));
        }

        return map;
    }

    private <T> T selectSingle(
            String sql,
            Supplier<T> noResultSupplier,
            ResultSetConverter<T> converter,
            Object... parameters) {

        List<T> list = this.selectList(sql, converter, parameters);

        if (list.isEmpty()) {
            log.debug("list is empty.");
            return noResultSupplier.get();
        } else if (list.size() != 1) {
            throw new MultiResultRecordException(sql, parameters);
        } else {
            return list.get(0);
        }
    }

    public <T> List<T> selectList(
            String sql,
            ResultSetConverter<T> converter,
            Object... parameters) {

        if (log.isDebugEnabled()) {
            log.debug("sql={}, parameters={}", sql, Arrays.toString(parameters));
        }

        try (PreparedStatement ps = this.con.prepareStatement(sql)) {
            int i = 0;
            for (Object parameter : parameters) {
                ps.setObject(++i, parameter);
            }

            try (ResultSet rs = ps.executeQuery()) {
                List<T> list = new ArrayList<>();

                while (rs.next()) {
                    list.add(converter.convert(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("sql=" + sql + ", parameters=" + Arrays.toString(parameters), e);
        }
    }

    @Override
    public void close() {
        try {
            this.con.close();
        } catch (SQLException e) {
            throw new RuntimeException("failed to close connection", e);
        }
    }
}
