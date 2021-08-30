package ru.job4j.accident.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.List;

@Service
public class AccidentTypeService {
    private final Logger logger = Logger.getLogger(AccidentTypeService.class);
    private final AccidentTypeRepository accidentTypeRepository;

    public AccidentTypeService(AccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    public List<AccidentType> findAll() {
        return accidentTypeRepository.findAll();
    }
}
