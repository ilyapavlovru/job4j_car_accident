package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem implements Store {
    private static final Store INST = new AccidentMem();

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public static Store instOf() {
        return INST;
    }

    private AccidentMem() {

        User user = new User();
        accidents.put(1, new Accident(1, "нарушение 1", "описание нарушения 1",
                "н777ту178", "СПБ, ул. Симонова, д. 1", null, "Принята", user));
        accidents.put(2, new Accident(2, "нарушение 2", "описание нарушения 2",
                "с008уу78", "СПБ, ул. Кустодиева, д. 25", null, "Принята", user));

    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }
}
