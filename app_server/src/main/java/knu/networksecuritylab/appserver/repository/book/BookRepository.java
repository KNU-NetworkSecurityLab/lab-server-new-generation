package knu.networksecuritylab.appserver.repository.book;

import knu.networksecuritylab.appserver.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookName(String bookName);

    List<Book> findByBookNameContainingIgnoreCase(String bookName);

    List<Book> findByBookAuthorContainingIgnoreCase(String bookAuthor);

    @Query("SELECT b FROM Book b WHERE b.bookName LIKE '%:searchWord%'")
    List<Book> findBookByName(String searchWord);
}
