package main.com.thoughtworks.twu.controller;

import com.thoughtworks.twu.controller.BookViewController;
import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.service.BookService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Ensures that
public class BookViewControllerTest {

    @Test
    public void shouldPassBookToBookViewPageWhenTheTitleIsValid(){
        BookService bookService = mock(BookService.class);
        String title = "title";
        String author = "author";
        String image = "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg";
        String description = "this is a book about magic, I love it very much!!";
        String ISBN10 = "0156027321";
        String ISBN13 = "978-0156027328";
        Book book = new Book(author, title, image, description, ISBN10, ISBN13);
        when(bookService.getBookByTitle(title)).thenReturn(book);

        BookViewController bookViewController = new BookViewController(bookService);
        ModelAndView modelAndView = bookViewController.viewBook(title);
        Book result = (Book) modelAndView.getModel().get("book");
        assertThat(result, equalTo(book));
    }
}
