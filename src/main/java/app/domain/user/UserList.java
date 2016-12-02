package app.domain.user;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
public class UserList {
    private List<User> list;

    public UserList(List<User> list) {
        if (list == null) {
            this.list = Collections.emptyList();
        } else {
            this.list = list;
        }
    }

    public List<User> toList() {
        return new ArrayList<>(this.list);
    }
}
