package app.common.util.database;

import java.util.Arrays;

public class MultiResultRecordException extends RuntimeException {

    public MultiResultRecordException(String sql, Object... parameters) {
        super("sql=" + sql + ", parameters=" + Arrays.toString(parameters));
    }
}
