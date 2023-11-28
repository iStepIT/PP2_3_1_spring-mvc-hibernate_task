package web.service;

import web.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> index ();
    Person show(int id);
    void save(Person person);
    void update(int id, Person updatedPerson);
    void delete(int id);

}
