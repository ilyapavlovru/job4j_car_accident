package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

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
}
