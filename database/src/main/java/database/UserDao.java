package database;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class UserDao implements Dao<User> {
    private ArrayList<User> users = new ArrayList<>();

    public Optional<User> get(long ID) {
        return Optional.ofNullable(users.get((int) ID));
    }

    public ArrayList<User> getAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }

    public void update(User user, String[] params)
    {
        user.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
        users.add(user);
    }

    public void delete(User user) {
        users.remove(user);
    }
}