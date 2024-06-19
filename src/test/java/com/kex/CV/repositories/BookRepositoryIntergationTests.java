
package com.kex.CV.repositories;

import com.kex.CV.TestDataUtil;
import com.kex.CV.domain.Author;
import com.kex.CV.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookRepositoryIntergationTests {

    private BookRepository underTest;

    @Autowired
    public  BookRepositoryIntergationTests(BookRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();

        Book bookA = TestDataUtil.createTestBookA(authorA);
        Book bookB = TestDataUtil.createTestBookB(authorA);
        Book bookC = TestDataUtil.createTestBookC(authorA);

        underTest.save(bookA);
        underTest.save(bookB);
        underTest.save(bookC);

        Iterable<Book> result = underTest.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);

        underTest.save(book);

        book.setTitle("UpdatedTitle");
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);

        underTest.save(book);

        underTest.delete(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isEmpty();
    }
}