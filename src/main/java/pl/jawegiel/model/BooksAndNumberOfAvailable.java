package pl.jawegiel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class BooksAndNumberOfAvailable {
    
    List<Book> books;
    int numberOfAvailable;
}
