package com.example.bookshelter.repository;

import com.example.bookshelter.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findBookByBookName (String bookName);
}
