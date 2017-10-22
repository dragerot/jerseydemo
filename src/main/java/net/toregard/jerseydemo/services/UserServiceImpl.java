package net.toregard.jerseydemo.services;

import net.toregard.jerseydemo.business.Computer;
import net.toregard.jerseydemo.business.ComputerPart;
import net.toregard.jerseydemo.business.ComputerPartDisplayVisitor;
import net.toregard.jerseydemo.domain.User;
import net.toregard.jerseydemo.domain.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static Map<Integer, User> DB = new HashMap<>();

    @Override
    public void create(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public Users listUser() {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());

        Users users = new Users();
        users.setUsers(new ArrayList<>(DB.values()));
        return users;
    }

    static
    {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Tore Gard");
        user1.setLastName("Wick");
        user1.setUri("/user-management/1");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Sturarargit");
        user2.setLastName("Potter");
        user2.setUri("/user-management/2");

        DB.put(user1.getId(), user1);
        DB.put(user2.getId(), user2);
    }
}
