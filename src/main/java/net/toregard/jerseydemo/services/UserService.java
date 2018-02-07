package net.toregard.jerseydemo.services;

import net.toregard.jerseydemo.domain.User;
import java.util.List;

public interface UserService {
    void get(String id);
    void update(User user);
    List<User> list();
}
