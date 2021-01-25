package pl.jawegiel.dao;

import pl.jawegiel.model.Book;
import pl.jawegiel.model.BooksAndNumberOfAvailable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookDao {
    
    void addBook(Book book) throws SQLException;
    void deleteBook(long id) throws SQLException;
    void deleteAllBooks() throws SQLException;
    Optional<BooksAndNumberOfAvailable> getAllBooks() throws SQLException;
    Optional<Long> findIdByTitleAuthorAndYear(String title, String author, int year) throws SQLException;
    Optional<Book> findBookById(long id) throws SQLException;
    Optional<List<Book>> findBooksByTitle(String title) throws SQLException;
    Optional<List<Book>> findBooksByAuthor(String author) throws SQLException;
    Optional<List<Book>> findBooksByYear(int year) throws SQLException;
    Optional<List<Book>> findBooksByTitleAndAuthor(String title, String author) throws SQLException;
    Optional<List<Book>> findBooksByTitleAndYear(String title, int year) throws SQLException;
    Optional<List<Book>> findBooksByAuthorAndYear(String author, int year) throws SQLException;
    Optional<Book> findBookByTitleAuthorAndYear(String title, String author, int year) throws SQLException;
    void lendBook(long id, String nameOfPerson) throws SQLException;
    void returnBook(long id, String nameOfPerson) throws SQLException;
}
