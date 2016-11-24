package app.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
@ToString
public class Id<T> implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Id(Long id) {
        this.id = Objects.requireNonNull(id);
    }

    @Deprecated
    protected Id() {}
}
