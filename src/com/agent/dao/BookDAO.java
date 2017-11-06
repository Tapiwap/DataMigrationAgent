/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agent.dao;

import com.agent.controllers.BookJpaController;
import com.agent.entity.Book;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tapiwa Pitso
 */
public class BookDAO {

    private final EntityManagerFactory emf;
    private final BookJpaController bookController;

    public BookDAO() {
        emf = Persistence.createEntityManagerFactory("DataMigrationAgentPU");
        bookController = new BookJpaController(emf);
    }

    public void addBook(Book book) {
        bookController.create(book);
    }

    public List<Book> getAllBooks() {
        return bookController.findBookEntities();
    }
}
