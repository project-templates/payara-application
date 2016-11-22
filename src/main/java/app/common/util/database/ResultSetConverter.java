package app.common.util.database;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetConverter<T> {
    T convert(ResultSet rs) throws SQLException;
}
