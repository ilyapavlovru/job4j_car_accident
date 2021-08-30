package ru.job4j.accident.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRuleRepository;

import java.util.List;

@Service
public class AccidentRuleService {
    private final Logger logger = Logger.getLogger(AccidentTypeService.class);
    private final AccidentRuleRepository accidentRuleRepository;

    public AccidentRuleService(AccidentRuleRepository accidentRuleRepository) {
        this.accidentRuleRepository = accidentRuleRepository;
    }

    public List<Rule> findAll() {
        return accidentRuleRepository.findAll();
    }
}
