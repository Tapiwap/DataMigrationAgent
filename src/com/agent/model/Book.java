/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agent.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tapiwa Pitso
 */
@XmlRootElement
public class Book implements Serializable {

    private String bookId, author, title, genre, description;
    private double price;
    private String publishDate;

    public Book() {
        this.bookId = null;
        this.author = null;
        this.title = null;
        this.genre = null;
        this.description = null;
        this.price = 0.00;
        this.publishDate = null;
    }

    public Book(String bookId, String author, String title, String genre, String description, double price, String publishDate) {
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.price = price;
        this.publishDate = publishDate;
    }

    public Book(String author, String title, String genre, String description, double price, String publishDate) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.price = price;
        this.publishDate = publishDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
