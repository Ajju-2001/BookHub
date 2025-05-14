package com.bookhub.jsp.bookhubservice;

import java.util.List;

import com.bookhub.jsp.bookdto.BookDto;
import com.bookhub.jsp.bookhubentitie.Book;
import com.bookhub.jsp.bookregisterlogindto.BookLoginDto;
import com.bookhub.jsp.bookregisterlogindto.BookRegisterDto;

public interface BookService {
	String registerUser(BookRegisterDto registerDto);
    String loginUser(BookLoginDto loginDto);
    String saveBook(BookDto bookDto);
    List<Book> getAllBooks();
	void deleteBookById(Integer id);
	Book getBookById(Integer bookId);
	void updateBook(Book book); 
}
