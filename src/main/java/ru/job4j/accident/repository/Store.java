package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface Store {

    Collection<Accident> findAllAccidents();

    void saveAccident(Accident accident);

    Optional<Accident> findAccidentById(int id);

    Collection<AccidentType> findAllAccidentTypes();

    Optional<AccidentType> findAccidentTypeById(int id);
}
