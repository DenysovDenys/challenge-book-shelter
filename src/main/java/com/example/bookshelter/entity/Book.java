package com.example.bookshelter.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "book", schema = "public")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "bookname")
    private String bookName;

    @Column(name = "dateofbook")
    private LocalDate dateOfBook;

    public Book(String bookName, LocalDate dateOfBook) {
        this.bookName = bookName;
        this.dateOfBook = dateOfBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", dateOfBook=" + dateOfBook +
                '}';
    }
}