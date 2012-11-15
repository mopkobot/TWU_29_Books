<@layout.block title="Search Results"  >

    <#if books?has_content>
    <p>Showing the top 20 results, sorted by relevance.</p>
    <ul class="book-list">
        <#list books as book>
            <li class="book">
                    <img class="book-picture" src="${book.image}"/>

                    <div class="book-content">
                        <h3 class="book-title">${book.title}</h3>

                        <div class="book-author">
                            <#if book.author?has_content>
                                by ${book.author}
                            </#if>
                        </div>
                    </div>
                    <form action="add_book" method="POST">
                        <input type="submit" name="view-book-btn" value="View Book" class="view-book-btn"/>
                        <input type = "hidden" name="author" value="${book.author}">
                        <input type = "hidden" name="title" value="${book.title}">
                        <input type = "hidden" name="image" value="${book.image}">
                        <input type = "hidden" name="description" value="${book.description}">
                        <input type = "hidden" name="ISBN10" value="${book.ISBN10}">
                        <input type = "hidden" name="ISBN13" value="${book.ISBN13}">
                    </form>
            </li>
        </#list>
    </ul>
    </#if>
    <#if error??>
    <p id="error">${error}</p>
    </#if>
</@layout.block>