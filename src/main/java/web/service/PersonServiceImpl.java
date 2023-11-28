package web.service;

import web.dao.PersonDAO;
import web.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class PersonServiceImpl implements PersonService {
    private final PersonDAO personDAO;

    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> index() {
        return personDAO.index();
    }

    @Override
    public Person show(int id) {
        return personDAO.show(id);
    }

    @Transactional
    @Override
    public void save(Person person) {
        personDAO.save(person);
    }

    @Transactional
    @Override
    public void update(int id, Person updatedPerson) {
        personDAO.update(id, updatedPerson);
    }
    @Transactional
    @Override
    public void delete(int id) {
        personDAO.delete(id);
    }
}
