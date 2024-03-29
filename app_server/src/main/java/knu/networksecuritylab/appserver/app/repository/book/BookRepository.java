package knu.networksecuritylab.appserver.app.repository.book;

import knu.networksecuritylab.appserver.app.entity.book.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookName(String bookName);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookTags bt" +
            " LEFT JOIN FETCH bt.tag WHERE b.id = :bookId")
    Optional<Book> findByIdWithBookTags(@Param("bookId") Long id);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.bookTags bt LEFT JOIN FETCH bt.tag ORDER BY RAND()")
    List<Book> findBookRandomList(Pageable pageable);

    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.bookTags bt LEFT JOIN FETCH bt.tag WHERE b " +
            "IN (SELECT b FROM Book b LEFT JOIN b.bookTags bt LEFT JOIN bt.tag t " +
            "WHERE b.bookName LIKE %:keyword% OR t.tagName LIKE %:keyword%)")
    List<Book> searchBookByName(@Param("keyword") String keyword);
}
