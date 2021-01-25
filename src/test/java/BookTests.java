import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jawegiel.model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTests {

    private static final long ID_FROM_CONSTRUCTOR = 1;
    private static final int YEAR_FROM_CONSTRUCTOR = 1992;
    private static final boolean AVAILABLE_FROM_CONSTRUCTOR = true;
    private static final long ID_FROM_SETTER = 5;
    private static final int YEAR_FROM_SETTER = 2010;
    private static final boolean AVAILABLE_FROM_SETTER = false;
    private static final String TITLE_FROM_CONSTRUCTOR = "Java for programmers";
    private static final String AUTHOR_FROM_CONSTRUCTOR = "John Doe";
    private static final String PERSON_WHO_LENT_FROM_CONSTRUCTOR = "";
    private static final String TITLE_FROM_SETTER = "Miami - guide";
    private static final String AUTHOR_FROM_SETTER = "James Bond";
    private static final String PERSON_WHO_LENT_FROM_SETTER = "Enrique Iglesias";
    private static final String BOOK_TO_STRING = "Book(id=1, title=Java for programmers, author=John Doe, personWhoLent=, year=1992, available=true)";
    private Book book;


    @BeforeEach
    void setUp() {
        book = new Book(ID_FROM_CONSTRUCTOR, TITLE_FROM_CONSTRUCTOR, AUTHOR_FROM_CONSTRUCTOR, YEAR_FROM_CONSTRUCTOR, AVAILABLE_FROM_CONSTRUCTOR, PERSON_WHO_LENT_FROM_CONSTRUCTOR);
    }

    @Test
    void testIfFieldsFromBookAreCorrect() {
        assertEquals(ID_FROM_CONSTRUCTOR, book.getId());
        assertEquals(TITLE_FROM_CONSTRUCTOR, book.getTitle());
        assertEquals(AUTHOR_FROM_CONSTRUCTOR, book.getAuthor());
        assertEquals(PERSON_WHO_LENT_FROM_CONSTRUCTOR, book.getPersonWhoLent());
        assertEquals(YEAR_FROM_CONSTRUCTOR, book.getYear());
        assertEquals(AVAILABLE_FROM_CONSTRUCTOR, book.isAvailable());
    }

    @Test
    void testIfBookSettersSetDataCorrectly() {
        book.setId(ID_FROM_SETTER);
        book.setTitle(TITLE_FROM_SETTER);
        book.setAuthor(AUTHOR_FROM_SETTER);
        book.setPersonWhoLent(PERSON_WHO_LENT_FROM_SETTER);
        book.setYear(YEAR_FROM_SETTER);
        book.setAvailable(AVAILABLE_FROM_SETTER);
        assertEquals(ID_FROM_SETTER, book.getId());
        assertEquals(TITLE_FROM_SETTER, book.getTitle());
        assertEquals(AUTHOR_FROM_SETTER, book.getAuthor());
        assertEquals(PERSON_WHO_LENT_FROM_SETTER, book.getPersonWhoLent());
        assertEquals(YEAR_FROM_SETTER, book.getYear());
        assertEquals(AVAILABLE_FROM_SETTER, book.isAvailable());
    }

    @Test
    void testIfBookToStringIsCorrect() {
        assertEquals(BOOK_TO_STRING, book.toString());
    }
}