package com.thoughtworks.twu.domain;

//Job: Understands literary work written by a certain person
public class Book {
    public static final String DEFAULT_IMAGE_SRC = "/twu/static/images/default_image.gif";
    public static final String DEFAULT_DESCRIPTION = "No description available.";
    private int id;
    private String author;
    private String title;
    private String image;
    private String description;
    private String ISBN10;
    private String ISBN13;

    public Book(String author, String title, String image, String description, String ISBN10, String ISBN13) {
        this.author = author;
        this.title = title;
        this.image = image;
        this.description = description;
        this.ISBN10 = ISBN10;
        this.ISBN13 = ISBN13;
    }

    private Book() {
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        if (description == null || description.trim().isEmpty()) {
            return DEFAULT_DESCRIPTION;
        }
        return description;
    }

    public String getISBN10() {
        return ISBN10;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public String getImage() {
        if (image == null || image.trim().isEmpty()) {
            return DEFAULT_IMAGE_SRC;
        }
        return image;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) return true;
        if (anotherObject == null || getClass() != anotherObject.getClass()) return false;

        Book thatBook = (Book) anotherObject;

        if (this.author != null ? !author.equals(thatBook.author) : thatBook.author != null) return false;
        if (this.image != null ? !image.equals(thatBook.image) : thatBook.image != null) return false;
        if (this.title != null ? !title.equals(thatBook.title) : thatBook.title != null) return false;
        if (this.description != null ? !description.equals(thatBook.description) : thatBook.description != null) return false;
        if (this.ISBN10 != null ? !ISBN10.equals(thatBook.ISBN10) : thatBook.ISBN10 != null) return false;
        if (this.ISBN13 != null ? !ISBN13.equals(thatBook.ISBN13) : thatBook.ISBN13 != null) return false;
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
