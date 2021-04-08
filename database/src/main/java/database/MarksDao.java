package database;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class MarksDao implements Dao<Marks> {
    private ArrayList<Marks> marks = new ArrayList<>();

    public Optional<Marks> get(long id) {
        return Optional.ofNullable(marks.get((int) id));
    }

    public ArrayList<Marks> getAll() {
        return marks;
    }

    public void save(Marks mark) {
        marks.add(mark);
    }

    public void update(Marks mark, String[] params)
    {
        mark.setMark(Objects.requireNonNull(params[0], "Mark cannot be less than 0"));
        marks.add(mark);
    }

    public void delete(Marks mark) {
        marks.remove(mark);
    }
}