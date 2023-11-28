package web.dao;

import web.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Person> index() {
        return entityManager.createQuery("SELECT u FROM Person u", Person.class).getResultList();
    }

    @Override
    public Person show(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void update(int id, Person updatePerson) {
        entityManager.merge(updatePerson);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(show(id));
    }
}
