package com.bookhub.jsp.bookhubrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookhub.jsp.bookhubentitie.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
