<@layout.block title="Search Results"  >
<form action="search_book" method="post">
    <#if searchValue??>
        <input type="text" name="searchValue" value="${searchValue}"
               maxlength="100"/>
    <#else>
        <input type="text" name="searchValue" maxlength="100"/>
    </#if>
    <select name="searchType">
        <option id="searchByTitle" value="title"
                <#if searchType?? && searchType  =
                "title">selected</#if>>Title</option>
        <option id="searchByAuthor" value="author"
                <#if searchType?? && searchType = "author">selected</#if>>Author</option>
        <option id="searchByISBN" value="isbn"
                <#if searchType?? && searchType = "isbn">selected</#if>>ISBN</option>
    </select>
    <input type="submit" value="Go!" id="search"/>
</form>
    <#if books?has_content>
        <p>Your search was sorted by relevance.</p>
        <ul class="book-list">
            <#list books as book>
                <li class="book">
                    <img class="book-picture" src="${book.image}"/>

                    <div class="book-content">
                        <h3 class="book-title">${book
                        .title}</h3>

                        <div class="book-author">
                            <#if book.author?has_content>
                                by ${book.author}
                            </#if>
                        </div>
                    </div>
                </li>
            </#list>
        </ul>
    </#if>
    <#if error??>
        <p id="error">${error}</p>
    </#if>
</@layout.block>