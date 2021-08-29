package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.*;

import java.util.Collection;
import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> findAllAccidentsWithRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct ac from ru.job4j.accident.model.Accident ac join fetch ac.rules", Accident.class)
                    .list();
        }
    }

    public Collection<AccidentType> findAllAccidentTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from ru.job4j.accident.model.AccidentType", AccidentType.class)
                    .list();
        }
    }

    public Collection<Rule> findAllRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from ru.job4j.accident.model.Rule", Rule.class)
                    .list();
        }
    }

    public void saveAccident(Accident accident, String[] rIds) {

        AccidentType accidentType = findAccidentTypeById(accident.getType().getId());
        accident.setType(accidentType);
        accident.setStatus("Принята");

        addAccident(accident, rIds);
    }

    private AccidentType findAccidentTypeById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .get(AccidentType.class, id);
        }
    }

    private Accident addAccident(Accident accident, String[] ruleIds) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            for (String id : ruleIds) {
                Rule rule = session.find(Rule.class, Integer.parseInt(id));
                accident.addRule(rule);
            }
            session.save(accident);
            session.getTransaction().commit();
            return accident;
        }
    }
}
