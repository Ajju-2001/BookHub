package com.bookhub.jsp.bookhubserviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookhub.jsp.bookdto.BookDto;
import com.bookhub.jsp.bookhubentitie.Book;
import com.bookhub.jsp.bookhubrepository.BookLoginRepository;
import com.bookhub.jsp.bookhubrepository.BookRegisterRepository;
import com.bookhub.jsp.bookhubrepository.BookRepository;
import com.bookhub.jsp.bookhubservice.BookService;
import com.bookhub.jsp.bookregisterlogin.BookLogin;
import com.bookhub.jsp.bookregisterlogin.BookRegister;
import com.bookhub.jsp.bookregisterlogindto.BookLoginDto;
import com.bookhub.jsp.bookregisterlogindto.BookRegisterDto;

@Service 
public class BookServiceImpl implements BookService{
	
	@Autowired
    private BookRegisterRepository registerRepository;

    @Autowired
    private BookLoginRepository loginRepository;
    
    @Autowired
    private BookRepository bookRepository;
	
	@Override 
	public String registerUser(BookRegisterDto dto) {

		try {
			if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
				return "Username is required!";
			}

			if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
				return "Password is required!";
			}

			if (dto.getGmail() == null || dto.getGmail().trim().isEmpty()) {
				return "Email is required!";
			}

			if (dto.getPhoneNumber() == null || dto.getPhoneNumber().trim().isEmpty()) {
				return "Phone number is required!";
			}

			if (!dto.getGmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				return "Invalid email format!";
			}

			if (!dto.getPhoneNumber().matches("\\d{10}")) {
				return "Phone number must be 10 digits!";
			}

			if (registerRepository.existsByUsername(dto.getUsername())) {
				return "Username already taken!";
			}

			BookRegister user = new BookRegister();
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			user.setGmail(dto.getGmail());
			user.setPhoneNumber(dto.getPhoneNumber());
			registerRepository.save(user);
			return "User registered successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return "An error occurred during registration.";
		}
	}
	
	@Override
	public String loginUser(BookLoginDto dto) {
	    try {
	        if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
	            return "Username is required!";
	        }

	        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
	            return "Password is required!";
	        }

	        BookRegister user = registerRepository.findByUsername(dto.getUsername());
	        if (user != null && user.getPassword().equals(dto.getPassword())) {
	            BookLogin login = new BookLogin();
	            login.setUsername(dto.getUsername());
	            login.setPassword(dto.getPassword()); 
	            loginRepository.save(login);
	            return "Login successful!"; 
	        }
	        return "Invalid username or password!";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "An error occurred during login.";
	    }
	}
	
	@Override
	public String saveBook(BookDto bookDto) {
		
	    if (bookDto.getTitle() == null || bookDto.getTitle().trim().isEmpty()) {
	        return "Title must not be empty";
	    }

	    if (bookDto.getAuthor() == null || bookDto.getAuthor().trim().isEmpty()) {
	        return "Author must not be empty";
	    }

	    if (bookDto.getPrice() <= 0) {
	        return "Price must be greater than zero";
	    }

	    if (bookDto.getDescription() == null || bookDto.getDescription().trim().isEmpty()) {
	        return "Description must not be empty";
	    }

	    Book book = new Book();
	    book.setBookId(bookDto.getBookId()); 
	    book.setTitle(bookDto.getTitle());
	    book.setAuthor(bookDto.getAuthor());
	    book.setPrice(bookDto.getPrice());
	    book.setDescription(bookDto.getDescription());

	    bookRepository.save(book);
	    return "Book saved successfully";
	}

	@Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();  
    }
	
	@Override
	public Book getBookById(Integer bookId) { 
	    Book book = bookRepository.findById(bookId).orElse(null);
	    if (book == null) {
	        throw new RuntimeException("Book not found with id " + bookId);
	    }
	    return book;
	}


	@Override
	public void updateBook(Book book) {
	    Book existing = bookRepository.findById(book.getBookId()).orElse(null);
	    if (existing != null) {
	        existing.setTitle(book.getTitle());
	        existing.setAuthor(book.getAuthor());
	        existing.setPrice(book.getPrice());
	        existing.setDescription(book.getDescription());
	        bookRepository.save(existing);
	    } else {
	        throw new RuntimeException("Book not found with id " + book.getBookId());
	    }
	}



	@Override
	public void deleteBookById(Integer id) {
	    bookRepository.deleteById(id);
	}

}
