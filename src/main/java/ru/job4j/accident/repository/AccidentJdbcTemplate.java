package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public List<Accident> findAllAccidentsWithRules() {
        List<Accident> accidents = jdbc.query("select accident.id, accident.name as accident_name, "
                        + "accident_rule.rule_id, rule.name as rule_name "
                        + "from accident left join accident_rule on accident.id = accident_rule.accident_id "
                        + "left join rule on accident_rule.rule_id = rule.id",
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

    public Optional<Accident> findAccidentById(int id) {
        return findAllAccidentsWithRules().stream()
                .filter(ac -> ac.getId() == id)
                .findAny();
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public Collection<Rule> findAllRules() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Accident saveAccident(Accident accident, String[] ruleIds) {

        for (String id : ruleIds) {
            Optional<Rule> optionalRule = findRuleById(Integer.parseInt(id));
            optionalRule.ifPresent(rule -> {
                accident.addRule(rule);
            });
        }

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into accident (name) values (?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            return ps;
        }, holder);

        accident.setId((int) holder.getKeys().get("id"));

        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "INSERT INTO accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(), rule.getId()
            );
        }

        return accident;
    }

    private Optional<Rule> findRuleById(int id) {
        Rule rule =  jdbc.queryForObject("select id, name from rule where id = ?",
                (rs, row) -> {
                    Rule foundRule = new Rule();
                    foundRule.setId(rs.getInt("id"));
                    foundRule.setName(rs.getString("name"));
                    return foundRule;
                }, id);
        return Optional.ofNullable(rule);
    }

    private Optional<AccidentType> findAccidentTypeById(int id) {
        AccidentType accidentType =  jdbc.queryForObject("select id, name from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType foundAccidentType = new AccidentType();
                    foundAccidentType.setId(rs.getInt("id"));
                    foundAccidentType.setName(rs.getString("name"));
                    return foundAccidentType;
                }, id);
        return Optional.ofNullable(accidentType);
    }

    public void updateAccident(Accident accident, String[] ruleIds) {

        for (String id : ruleIds) {
            Optional<Rule> optionalRule = findRuleById(Integer.parseInt(id));
            optionalRule.ifPresent(rule -> {
                accident.addRule(rule);
            });
        }

        jdbc.update("update accident set name = ? where id = ?",
                accident.getName(),
                accident.getId()
        );

        jdbc.update("delete from accident_rule where accident_id = ?",
                accident.getId());

        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "INSERT INTO accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(), rule.getId()
            );
        }
    }

//    public Optional<Accident> findAccidentById(int id) {
//        Accident accident =  jdbc.queryForObject("select id, name from accident where id = ?",
//                (rs, row) -> {
//                    Accident foundAccident = new Accident();
//                    foundAccident.setId(rs.getInt("id"));
//                    foundAccident.setName(rs.getString("name"));
//                    return foundAccident;
//                }, id);
//        return Optional.ofNullable(accident);
//    }
}
