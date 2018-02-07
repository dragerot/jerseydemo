package net.toregard.jerseydemo.services;

import net.toregard.jerseydemo.domain.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void get(String id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> list() {
        return null;
    }
/*
      Users users = new Users();
        users.setUsers(new ArrayList<>(DB.values()));
        return users;
     */
}
