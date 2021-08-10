package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("insert into accident (name) values (?)",
                accident.getName());
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

    public List<Accident> getAllAccidentsWithRules() {
        List<Accident> accidents = jdbc.query("select accident.id, accident.name as accident_name, "
                        + "item_category.rule_id, rule.name as rule_name "
                        + "from accident left join item_category on accident.id = item_category.accident_id "
                        + "left join rule on item_category.rule_id = rule.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("accident_name"));
                    accident.addRule(Rule.of(rs.getInt("rule_id"), rs.getString("rule_name")));
                    return accident;
                });
        return addRulesToAccidents(accidents);
    }

    private List<Accident> addRulesToAccidents(List<Accident> accidents) {
        List<Accident> result = new ArrayList<>();
        for (Accident accident : accidents) {
            Accident foundAccident = findAccidentInList(accident, result);
            if (foundAccident == null) {
                result.add(accident);
            } else {
                foundAccident.addRule(accident.getRules().iterator().next());
            }
        }
        return result;
    }

    private Accident findAccidentInList(Accident accident, List<Accident> result) {
        return result.stream()
                .filter(ac -> ac.getId() == accident.getId())
                .findAny()
                .orElse(null);
    }
}
