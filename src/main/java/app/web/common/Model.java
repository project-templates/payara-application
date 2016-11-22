package app.web.common;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Model implements Serializable {

    private final HttpServletRequest request;
    private final Map<String, Object> values = new HashMap<>();

    public Model(HttpServletRequest request) {
        this.request = Objects.requireNonNull(request);
        this.request.setAttribute("model", this.values);
    }

    public void put(String name, Object value) {
        this.values.put(name, value);
    }
}
