package net.toregard.jerseydemo.services;

import net.toregard.jerseydemo.domain.User;
import net.toregard.jerseydemo.domain.Users;

import java.util.List;

public interface UserService {
    void create(User user);
    void delete(int id);
    void update(User user);
    Users listUser();
}
