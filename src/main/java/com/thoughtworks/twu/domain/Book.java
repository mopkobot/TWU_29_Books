package com.thoughtworks.twu.domain;

import java.io.Serializable;

//Understands a row in table book of readerfeeder
public class Book implements Serializable {
    private int id;
    private String author;
    private String title;
    private String image = "http://smartmobilestudio.com/wp-content/uploads/2012/06/leather-book-preview.png";
    private String description = "No description available.";
    private String ISBN10;
    private String ISBN13;

    public String getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }

    public Book(String author, String title, String image, String description, String ISBN10, String ISBN13) {
        if (author == null) {
            throw new IllegalArgumentException("No author");
        }
        if (title == null) {
            throw new IllegalArgumentException("No Title");
        }
        this.author = author;
        this.title = title;
        if(image != null) this.image = image;
        if(description != null) this.description = description;
        if(ISBN10 == null && ISBN13 == null){
            throw new IllegalArgumentException("No ISBN");
        }
        this.ISBN10 = ISBN10;
        this.ISBN13 = ISBN13;
    }

    private Book() {
    }

    public boolean isImageAvailable() {
        return this.image != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (image != null ? !image.equals(book.image) : book.image != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (ISBN10 != null ? !ISBN10.equals(book.ISBN10) : book.ISBN10 != null) return false;
        if (ISBN13 != null ? !ISBN13.equals(book.ISBN13) : book.ISBN13 != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ISBN10 != null ? ISBN10.hashCode() : 0);
        result = 31 * result + (ISBN13 != null ? ISBN13.hashCode() : 0);
        return result;
    }
}
