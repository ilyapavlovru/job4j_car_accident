package ru.job4j.accident.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final Logger logger = Logger.getLogger(AccidentService.class);
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final AccidentRuleRepository accidentRuleRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository,
                           AccidentRuleRepository accidentRuleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.accidentRuleRepository = accidentRuleRepository;
    }

    public void saveAccident(Accident accident, String[] ruleIds) {

        Optional<AccidentType> accidentType = accidentTypeRepository.findById(accident.getType().getId());
        accident.setType(accidentType.get());

        for (String id : ruleIds) {
            Rule rule = accidentRuleRepository.findById(Integer.parseInt(id)).get();
            accident.addRule(rule);
        }
        accidentRepository.save(accident);
    }

    public Optional<Accident> findAccidentById(int id) {
        return accidentRepository.findById(id);
    }

    public List<Accident> findAllAccidents() {
        return accidentRepository.findAll();
    }
}
