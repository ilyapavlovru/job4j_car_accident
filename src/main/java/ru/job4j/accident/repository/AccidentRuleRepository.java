package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface AccidentRuleRepository extends CrudRepository<Rule, Integer> {
    List<Rule> findAll();
}
