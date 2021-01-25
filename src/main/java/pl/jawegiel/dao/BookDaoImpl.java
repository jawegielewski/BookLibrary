package pl.jawegiel.dao;

import pl.jawegiel.model.Book;
import pl.jawegiel.model.BooksAndNumberOfAvailable;
import pl.jawegiel.utility.BooksTableCreator;
import pl.jawegiel.utility.BooksTableHelper;
import pl.jawegiel.utility.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BookDaoImpl implements BookDao {

    private Connection connection;
    private PreparedStatement ps = null;
    private Book book = null;

    public BookDaoImpl() throws SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
        if (!BooksTableHelper.isTableExist(connection, "books")) {
            BooksTableCreator.createBooksTable(connection);
        }
    }

    private boolean checkIfBookAlreadyExists(Book book) throws SQLException {
        int count = 0;
        ps = connection.prepareStatement("select count(*) as total from BOOKS where TITLE='" + book.getTitle() + "' and AUTHOR='" + book.getAuthor() + "' and \"YEAR\"=" + book.getYear() + "");
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            count = rs.getInt("total");
        return count > 0;
    }


    @Override
    public void addBook(Book book) throws SQLException {
        connection = getConnection();
        try {
            if (!checkIfBookAlreadyExists(book)) {
                String queryString = "INSERT INTO BOOKS(TITLE, AUTHOR, \"YEAR\", AVAILABLE, PERSON_WHO_LENT) VALUES(?,?,?,?,?)";
                ps = connection.prepareStatement(queryString);
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setInt(3, book.getYear());
                ps.setBoolean(4, true);
                ps.setString(5, "");
                ps.executeUpdate();
                System.out.println("Book with id " + findIdByTitleAuthorAndYear(book.getTitle(), book.getAuthor(), book.getYear()).orElse(null) + " has been added.");
            } else
                System.out.println("Book already exists");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteBook(long id) throws SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
        ps = connection.prepareStatement("SELECT * from BOOKS WHERE ID =" + id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            book = new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getBoolean(5), rs.getString(6));
        }
        if (book != null) {
            if (!book.getPersonWhoLent().equals(""))
                System.out.println("Book with given ID cannot be deleted because is lent.");
            else {
                Statement st = connection.createStatement();
                st.executeUpdate("DELETE FROM BOOKS WHERE ID =" + id);
            }
        } else
            System.out.println("Book with given ID cannot be deleted because does not exist.");

        connection.commit();
        ps.close();
        connection.close();

    }


    @Override
    public void deleteAllBooks() throws SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
        if (Objects.requireNonNull(getAllBooks().orElse(null)).getBooks().size() == getAllBooks().get().getNumberOfAvailable()) {
            ps = getConnection().prepareStatement("TRUNCATE TABLE BOOKS");
            ps.executeUpdate();
            connection.commit();
            ps.close();
            System.out.println("All books has been deleted.");
        } else
            System.out.println("Books cannot be deleted because one or more books are not returned back.");

        connection.close();
    }

    @Override
    public Optional<BooksAndNumberOfAvailable> getAllBooks() throws SQLException {
        connection = getConnection();
        List<Book> allBooks = new ArrayList<>();
        String queryString = "SELECT * from BOOKS";
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            allBooks.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getBoolean(5), rs.getString(6)));
        }
        int numberOfAvailable = 0;
        for (Book b : allBooks) {
            if (b.isAvailable()) numberOfAvailable++;
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.of(new BooksAndNumberOfAvailable(allBooks, numberOfAvailable));
    }


    @Override
    public Optional<Long> findIdByTitleAuthorAndYear(String title, String author, int year) throws SQLException {
        connection = getConnection();
        long id = 0;
        String queryString = "SELECT ID from BOOKS WHERE TITLE = '" + title + "' and AUTHOR = '" + author + "' and \"YEAR\" = " + year + "";
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getLong(1);
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.of(id);
    }

    @Override
    public Optional<Book> findBookById(long id) throws SQLException {
        connection = getConnection();
        Book bookById = null;
        String queryString = "SELECT * from BOOKS WHERE ID =" + id;
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            bookById = new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3));
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.ofNullable(bookById);
    }

    @Override
    public Optional<List<Book>> findBooksByTitle(String title) throws SQLException {
        connection = getConnection();
        List<Book> booksByTitle = new ArrayList<>();
        String queryString = "SELECT * from BOOKS WHERE TITLE = '" + title + "'";
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            booksByTitle.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3)));
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.of(booksByTitle);
    }

    @Override
    public Optional<List<Book>> findBooksByAuthor(String author) throws SQLException {
        connection = getConnection();
        List<Book> booksByAuthor = new ArrayList<>();
        String queryString = "SELECT * from BOOKS WHERE AUTHOR = '" + author + "'";
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            booksByAuthor.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3)));
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.of(booksByAuthor);
    }

    @Override
    public Optional<List<Book>> findBooksByYear(int year) throws SQLException {
        connection = getConnection();
        List<Book> booksByYear = new ArrayList<>();
        String queryString = "SELECT * from BOOKS WHERE \"YEAR\" = " + year;
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            booksByYear.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3)));
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.of(booksByYear);
    }

    @Override
    public Optional<List<Book>> findBooksByTitleAndAuthor(String title, String author) throws SQLException {
        connection = getConnection();
        List<Book> booksByYear = new ArrayList<>();
        String queryString = "SELECT * from BOOKS WHERE TITLE = '" + title + "' and AUTHOR = '" + author + "'";
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            booksByYear.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3)));
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.of(booksByYear);
    }

    @Override
    public Optional<List<Book>> findBooksByTitleAndYear(String title, int year) throws SQLException {
        connection = getConnection();
        List<Book> booksByYear = new ArrayList<>();
        String queryString = "SELECT * from BOOKS WHERE TITLE = '" + title + "' and \"YEAR\" = " + year;
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            booksByYear.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3)));
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.of(booksByYear);
    }

    @Override
    public Optional<List<Book>> findBooksByAuthorAndYear(String author, int year) throws SQLException {
        connection = getConnection();
        List<Book> booksByYear = new ArrayList<>();
        String queryString = "SELECT * from BOOKS WHERE AUTHOR = '" + author + "' and \"YEAR\" = " + year;
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            booksByYear.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3)));
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.of(booksByYear);
    }

    @Override
    public Optional<Book> findBookByTitleAuthorAndYear(String title, String author, int year) throws SQLException {
        connection = getConnection();
        Book bookByTitleAuthorAndYear = null;
        String queryString = "SELECT * from BOOKS WHERE TITLE = '" + title + "' and AUTHOR = '" + author + "' and \"YEAR\" = " + year + "";
        ps = connection.prepareStatement(queryString);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            bookByTitleAuthorAndYear = new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3));
        }
        connection.commit();
        ps.close();
        connection.close();
        return Optional.ofNullable(bookByTitleAuthorAndYear);
    }

    @Override
    public void lendBook(long id, String nameOfPerson) throws SQLException {
        connection = getConnection();
        ps = connection.prepareStatement("SELECT * from BOOKS WHERE ID =" + id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            book = new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getBoolean(5), rs.getString(6));
        }
        if (book != null) {
            if (book.getPersonWhoLent().equals("")) {
                ps = connection.prepareStatement("update BOOKS SET AVAILABLE = (false), PERSON_WHO_LENT =('" + nameOfPerson + "') WHERE ID =" + id);
                ps.executeUpdate();
                System.out.println("Book with id " + book.getId() + " has been successfully lent.");
            } else
                System.out.println("Book cannot be lent because is already lent by " + book.getPersonWhoLent());
        }
        connection.commit();
        ps.close();
        connection.close();
    }

    @Override
    public void returnBook(long id, String nameOfPerson) throws SQLException {
        connection = getConnection();
        Book book = null;
        ps = connection.prepareStatement("SELECT * from BOOKS WHERE ID =" + id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            book = new Book(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getInt(3), rs.getBoolean(5), rs.getString(6));
        }

        if (book != null) {
            if (book.getPersonWhoLent().equals(nameOfPerson)) {
                ps = connection.prepareStatement("update BOOKS SET AVAILABLE = (true), PERSON_WHO_LENT =('') WHERE ID =" + id);
                ps.executeUpdate();
                System.out.println("Book with id " + book.getId() + " has been returned.");
            } else if (!book.getPersonWhoLent().equals(nameOfPerson) && !book.getPersonWhoLent().equals(""))
                System.out.println("Book is lent by another person.");
        }
        connection.commit();
        ps.close();
        connection.close();
    }

    private Connection getConnection() throws SQLException {
        return connection = ConnectionFactory.getInstance().getConnection();
    }
}
