package com.bookhub.jsp.bookhubrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookhub.jsp.bookregisterlogin.BookRegister;

public interface BookRegisterRepository extends JpaRepository<BookRegister, Integer>{

	BookRegister findByUsername(String username);

	boolean existsByUsername(String username);   
	
}
