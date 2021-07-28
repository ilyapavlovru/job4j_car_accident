package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store {
    private static final Store INST = new AccidentMem();

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final static AtomicInteger ACCIDENT_ID = new AtomicInteger(2);

    public static Store instOf() {
        return INST;
    }

    private AccidentMem() {

        User user = new User();

        Accident accident1 = new Accident("нарушение 1");
        accident1.setId(1);
        accident1.setDescription("описание нарушения 1");
        accident1.setCarNumber("н777ту178");
        accident1.setAddress("СПБ, ул. Симонова, д. 1");
        accident1.setImage(null);
        accident1.setStatus("Принята");
        accident1.setCreated(new Date(System.currentTimeMillis()));
        accident1.setUser(user);

        Accident accident2 = new Accident("нарушение 2");
        accident2.setId(2);
        accident2.setDescription("описание нарушения 2");
        accident2.setCarNumber("с008уу78");
        accident2.setAddress("СПБ, ул. Кустодиева, д. 25");
        accident2.setImage(null);
        accident2.setStatus("Принята");
        accident2.setCreated(new Date(System.currentTimeMillis()));
        accident2.setUser(user);

        accidents.put(1, accident1);
        accidents.put(2, accident2);
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    @Override
    public void saveAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENT_ID.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Optional<Accident> findAccidentById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }
}
