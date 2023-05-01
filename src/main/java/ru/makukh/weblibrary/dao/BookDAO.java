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
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM BOOK", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO BOOK(name, author, year) VALUES (?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE BOOK SET name=?, author=?, year=? where id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM BOOK WHERE ID=?", id);
    }

    public Optional<Person> searchOwner(int id) {
        return jdbcTemplate.query("SELECT p.id, p.name, p.birth_date FROM PERSON p JOIN BOOK b ON p.id = b.person_id WHERE b.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE BOOK SET person_id = null where id=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE BOOK SET person_id = ? where id = ?" ,
                selectedPerson.getId(),
                id);
    }
}
