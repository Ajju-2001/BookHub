package com.bookhub.jsp.bookhubrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookhub.jsp.bookregisterlogin.BookLogin;

public interface BookLoginRepository extends JpaRepository<BookLogin, Integer>{ 

}
