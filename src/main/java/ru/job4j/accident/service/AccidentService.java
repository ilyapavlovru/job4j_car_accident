package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.Store;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccidentService {

    private Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public Collection<Accident> findAllAccidents() {
        return store.findAllAccidents();
    }

    public void saveAccident(Accident accident) {
        store.saveAccident(accident);
    }

    public Optional<Accident> findById(int id) {
        return store.findAccidentById(id);
    }
}
