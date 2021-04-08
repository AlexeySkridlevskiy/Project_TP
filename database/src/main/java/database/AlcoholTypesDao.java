package database;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class AlcoholTypesDao implements Dao<AlcoholTypes> {
    private ArrayList<AlcoholTypes> types = new ArrayList<>();

    public Optional<AlcoholTypes> get(long id) {
        return Optional.ofNullable(types.get((int) id));
    }

    public ArrayList<AlcoholTypes> getAll() {
        return types;
    }

    public void save(AlcoholTypes alcoholTypes) {
        types.add(alcoholTypes);
    }

    public void update(AlcoholTypes alcoholTypes, String[] params)
    {
        alcoholTypes.setType(Objects.requireNonNull(params[0], "Name cannot be null"));
        types.add(alcoholTypes);
    }

    public void delete(AlcoholTypes alcohol) {
        types.remove(alcohol);
    }
}