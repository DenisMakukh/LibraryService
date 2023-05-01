package ru.makukh.weblibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.makukh.weblibrary.models.Book;
import ru.makukh.weblibrary.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE ID=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO PERSON(name, birth_date) VALUES (?, ?)",
                person.getName(),
                person.getBirthDate());
    }

    public void update(int id, Person updatedPerson) {

        jdbcTemplate.update("UPDATE PERSON SET name=?, birth_date=? WHERE ID=?",
                updatedPerson.getName(),
                updatedPerson.getBirthDate(),
                id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE ID=?", id);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE NAME=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> searchBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}

