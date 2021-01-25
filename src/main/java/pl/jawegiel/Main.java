package pl.jawegiel;

import pl.jawegiel.dao.BookDao;
import pl.jawegiel.dao.BookDaoImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    

    public static void main(String[] args) throws SQLException {
        BookDao bookDao = new BookDaoImpl();
        System.out.println(System.lineSeparator() + "All books:" + System.lineSeparator() + bookDao.getAllBooks().toString() + System.lineSeparator());

        
        System.out.println("Input ID of book to find:");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        System.out.println(System.lineSeparator() + bookDao.findBookById(Long.parseLong(id)).toString());
    }
}
