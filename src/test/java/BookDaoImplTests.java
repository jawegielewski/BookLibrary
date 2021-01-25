import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jawegiel.dao.BookDaoImpl;
import pl.jawegiel.model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookDaoImplTests {

    private static final String BOOK_TITLE = "Java advanced";
    private static final String BOOK_AUTHOR = "John Doe";
    private static final int BOOK_YEAR = 2020;

    private static final String BOOK_2_TITLE = "Lorem ipsum";
    private static final String BOOK_2_AUTHOR = "John Doe";
    private static final int BOOK_2_YEAR = 2019;

    private static final String BOOK_3_TITLE = "IntelliJ IDEA Essentials";
    private static final String BOOK_3_AUTHOR = "John Doe";
    private static final int BOOK_3_YEAR = 2019;

    private static final String BOOK_4_TITLE = "Miami - guide";
    private static final String BOOK_4_AUTHOR = "James Bond";
    private static final int BOOK_4_YEAR = 1999;

    private static final String BOOK_FOR_DELETION_TITLE = "Sample book";
    private static final String BOOK_FOR_DELETION_AUTHOR = "Martin Smith";
    private static final int BOOK_FOR_DELETION_YEAR = 1960;

    private static final String NAME_OF_PERSON_WHO_LEND_A_BOOK = "Jakob Dylan";

    BookDaoImpl bookDao;

    @BeforeEach
    void setUp() throws SQLException {
        bookDao = new BookDaoImpl();
    }


    @Test
    void testIfSearchBookByIdWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        Optional<Long> id = bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR);
        Optional<Book> searchedBook = bookDao.findBookById(Objects.requireNonNull(id.orElse(null)));

        assertNotNull(searchedBook.orElse(null));
    }

    @Test
    void testIfSearchBooksByTitleWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        bookDao.addBook(new Book(BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_YEAR));
        bookDao.addBook(new Book(BOOK_3_TITLE, BOOK_3_AUTHOR, BOOK_3_YEAR));
        bookDao.addBook(new Book(BOOK_4_TITLE, BOOK_4_AUTHOR, BOOK_4_YEAR));
        Optional<List<Book>> searchedBook = bookDao.findBooksByTitle("Java advanced");

        assertEquals(1, Objects.requireNonNull(searchedBook.orElse(null)).size());
    }

    @Test
    void testIfSearchBooksByAuthorWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        bookDao.addBook(new Book(BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_YEAR));
        bookDao.addBook(new Book(BOOK_3_TITLE, BOOK_3_AUTHOR, BOOK_3_YEAR));
        bookDao.addBook(new Book(BOOK_4_TITLE, BOOK_4_AUTHOR, BOOK_4_YEAR));
        Optional<List<Book>> searchedBook = bookDao.findBooksByAuthor("John Doe");

        assertEquals(3, Objects.requireNonNull(searchedBook.orElse(null)).size());
    }

    @Test
    void testIfSearchBooksByYearWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        bookDao.addBook(new Book(BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_YEAR));
        bookDao.addBook(new Book(BOOK_3_TITLE, BOOK_3_AUTHOR, BOOK_3_YEAR));
        bookDao.addBook(new Book(BOOK_4_TITLE, BOOK_4_AUTHOR, BOOK_4_YEAR));
        Optional<List<Book>> searchedBook = bookDao.findBooksByYear(2019);

        assertEquals(2, Objects.requireNonNull(searchedBook.orElse(null)).size());
    }

    @Test
    void testIfSearchBooksByTitleAndAuthorWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        bookDao.addBook(new Book(BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_YEAR));
        bookDao.addBook(new Book(BOOK_3_TITLE, BOOK_3_AUTHOR, BOOK_3_YEAR));
        bookDao.addBook(new Book(BOOK_4_TITLE, BOOK_4_AUTHOR, BOOK_4_YEAR));
        Optional<List<Book>> searchedBook = bookDao.findBooksByTitleAndAuthor("Java advanced", "John Doe");

        assertEquals(1, Objects.requireNonNull(searchedBook.orElse(null)).size());
    }

    @Test
    void testIfSearchBooksByTitleAndYearWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        bookDao.addBook(new Book(BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_YEAR));
        bookDao.addBook(new Book(BOOK_3_TITLE, BOOK_3_AUTHOR, BOOK_3_YEAR));
        bookDao.addBook(new Book(BOOK_4_TITLE, BOOK_4_AUTHOR, BOOK_4_YEAR));
        Optional<List<Book>> searchedBook = bookDao.findBooksByTitleAndYear("Java advanced", 2020);

        assertEquals(1, Objects.requireNonNull(searchedBook.orElse(null)).size());
    }

    @Test
    void testIfSearchBooksByAuthorAndYearWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        bookDao.addBook(new Book(BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_YEAR));
        bookDao.addBook(new Book(BOOK_3_TITLE, BOOK_3_AUTHOR, BOOK_3_YEAR));
        bookDao.addBook(new Book(BOOK_4_TITLE, BOOK_4_AUTHOR, BOOK_4_YEAR));
        Optional<List<Book>> searchedBook = bookDao.findBooksByAuthorAndYear("John Doe", 2019);

        assertEquals(2, Objects.requireNonNull(searchedBook.orElse(null)).size());
    }

    @Test
    void testIfSearchBookByTitleAuthorAndYearWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        Optional<Book> searchedBook = bookDao.findBookByTitleAuthorAndYear("Java advanced", "John Doe", 2020);

        assertNotNull(Objects.requireNonNull(searchedBook.orElse(null)));
    }

    @Test
    void testIfDeletingBookWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_FOR_DELETION_TITLE, BOOK_FOR_DELETION_AUTHOR, BOOK_FOR_DELETION_YEAR));
        bookDao.deleteBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_FOR_DELETION_TITLE, BOOK_FOR_DELETION_AUTHOR, BOOK_FOR_DELETION_YEAR).orElse(null)));

        assertEquals(0, Objects.requireNonNull(bookDao.getAllBooks().orElse(null)).getBooks().size());
    }

    @Test
    void testIfLendingBookWorksCorrectly() throws SQLException {
        bookDao.returnBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);
        bookDao.deleteAllBooks();
        bookDao.addBook(new Book(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR));
        bookDao.lendBook(Objects.requireNonNull(bookDao.findIdByTitleAuthorAndYear(BOOK_TITLE, BOOK_AUTHOR, BOOK_YEAR).orElse(null)), NAME_OF_PERSON_WHO_LEND_A_BOOK);

        assertEquals(1, Objects.requireNonNull(bookDao.getAllBooks().orElse(null)).getBooks().size() - bookDao.getAllBooks().get().getNumberOfAvailable());
    }
}
