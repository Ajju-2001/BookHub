package com.bookhub.jsp.bookhubcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.bookhub.jsp.bookdto.BookDto;
import com.bookhub.jsp.bookhubentitie.Book;
import com.bookhub.jsp.bookhubservice.BookService;
import com.bookhub.jsp.bookregisterlogindto.BookLoginDto;
import com.bookhub.jsp.bookregisterlogindto.BookRegisterDto;

@Controller
public class BoockController {
	 
	@Autowired
    private BookService bookService;
	

	@GetMapping("/register")
    public String showRegisterForm(Model model) {
        try {
            model.addAttribute("registerDto", new BookRegisterDto());
            return "register";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while loading the registration form.");
            return "error";  
        }
    }

	
	@PostMapping("/register")
	public String register(@ModelAttribute BookRegisterDto registerDto, Model model) {
	    try {
	        String message = bookService.registerUser(registerDto);
	        model.addAttribute("message", message);
	       
	        return "redirect:/login"; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", "Registration failed. Please try again.");
	        return "register"; 
	    }
	}

	
	@GetMapping("/login")
    public String showLoginForm(Model model) {
        try {
            model.addAttribute("loginDto", new BookLoginDto());
            return "login"; 
        } catch (Exception e) {
            e.printStackTrace(); 
            model.addAttribute("error", "An error occurred while loading the login page.");
            return "error"; 
        }
    }

	@PostMapping("/login")
    public String login(@ModelAttribute BookLoginDto loginDto, Model model) {
        try {
            String message = bookService.loginUser(loginDto);
            if (message.equals("Login successful!")) {
                model.addAttribute("username", loginDto.getUsername());
                return "redirect:/bookForm"; 
            } else {
                model.addAttribute("error", message);
                return "login"; 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            model.addAttribute("error", "An unexpected error occurred. Please try again.");
            return "login"; 
        }
    }
	
	@GetMapping("/bookForm")
	public String showBookForm(Model model) {
		System.err.println("Start");
	    model.addAttribute("bookDto", new BookDto());  
	    List<Book> books = bookService.getAllBooks();  
	    model.addAttribute("books", books);  
	    return "bookform"; 
	}
	
	@PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("bookDto") BookDto bookDto, Model model) {
        try {
            bookService.saveBook(bookDto);  
            List<Book> books = bookService.getAllBooks();  
            model.addAttribute("books", books);  
            model.addAttribute("bookDto", new BookDto()); 
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while saving the book.");
        }
        return "bookform"; 
    }

	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") Integer id, Model model) { 
	    Book book = bookService.getBookById(id);
	    model.addAttribute("bookDto", book); 
	    return "update"; 
	}
	
	@PostMapping("/updateBook")
	public String updateBook(@ModelAttribute Book book) {
	    bookService.updateBook(book);
	    return "redirect:/bookForm"; 
	}

	
	@PostMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") Integer id, Model model) {
	    bookService.deleteBookById(id);
	    model.addAttribute("bookDto", new BookDto());
	    model.addAttribute("books", bookService.getAllBooks());
	    return "bookform"; 
	}

}
