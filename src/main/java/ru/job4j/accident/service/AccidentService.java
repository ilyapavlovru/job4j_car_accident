package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
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

    public void saveAccident(Accident accident, String[] ruleIds) {
        Optional<AccidentType> accidentType = store.findAccidentTypeById(accident.getType().getId());
        accidentType.ifPresent(value -> accident.setType(accidentType.get()));

        for (String id : ruleIds) {
            Optional<Rule> optionalRule = store.findRuleById(Integer.parseInt(id));
            optionalRule.ifPresent(rule -> {
                accident.addRule(rule);
            });

        }

        store.saveAccident(accident);
    }

    public Optional<Accident> findById(int id) {
        return store.findAccidentById(id);
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return store.findAllAccidentTypes();
    }

    public Collection<Rule> findAllRules() {
        return store.findAllRules();
    }
}
