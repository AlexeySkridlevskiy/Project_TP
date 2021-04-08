package database;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class AlcoholDao implements Dao<Alcohol> {
    private ArrayList<Alcohol> drinks = new ArrayList<>();

    public Optional<Alcohol> get(long ID) {
        return Optional.ofNullable(drinks.get((int) ID));
    }

    public ArrayList<Alcohol> getAll() {
        return drinks;
    }

    public void save(Alcohol alcohol) {
        drinks.add(alcohol);
    }

    public void update(Alcohol alcohol, String[] params)
    {
        alcohol.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
        drinks.add(alcohol);
    }

    public void delete(Alcohol alcohol) {
        drinks.remove(alcohol);
    }
}