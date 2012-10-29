package com.thoughtworks.twu.domain;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

//Ensures book domain satisfy client's requirement
public class BookTest {

    @Test
    public void shouldHaveAuthor() {
        Book book = new Book("author", "title", "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg", null, null, "978-0156027328");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotHaveInvalidAuthor() {
        Book book = new Book(null, "title", "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg", null, null, "978-0156027328");
    }

    @Test
    public void shouldHaveTitle() {
        Book book = new Book("author", "title", "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg", null, null, "978-0156027328");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotHaveInvalidTitle() {
        Book book = new Book("author", null, "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg", null, null, "978-0156027328");
    }

    @Test
    public void shouldHaveImageFromAmazon() {
        Book book = new Book("author", "title", "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg", null, null, "978-0156027328");
    }

    @Test
    public void shouldHaveDefaultImage() {
        Book book = new Book("author", "title", null, null, null, "978-0156027328");
        assertThat(book.isImageAvailable(), equalTo(true));
    }

    @Test
    public void shouldHaveShortDescription() {
        Book book = new Book("author", "title", null, "this is a book about magic, I love it very much!!", null, "978-0156027328");
    }

    @Test
    public void shouldHaveDefaultDescriptionWhenNoDescriptionAvailable() {
        Book book = new Book("author", "title", null, null, null, "978-0156027328");
        Book expected = new Book("author", "title", null, "No description available.", null, "978-0156027328");
        assertThat(book, equalTo(expected));
    }

    @Test
    public void shouldHaveISBN10IfItIsAvailable() {
        Book book = new Book("author", "title", null, null, "0156027321", "978-0156027328");
    }

    @Test
    public void shouldHaveISBN13IfItIsAvailable() {
        Book book = new Book("author", "title", null, null, null, "978-0156027328");
    }

    @Test
    public void shouldHaveBothISBN10AndISBN13WhenTheyAreAvailable() {
        Book book = new Book("author", "title", null, null, "0156027321", "978-0156027328");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotHaveNoneISBN10NorISBN13() {
        Book book = new Book("author", "title", null, null, null, null);
    }

}
