<@layout.block title="Search Results"  >
<form action="search_book" method="post">
<#if searchValue??>
    <input type="text" name="searchValue" value="${searchValue}"
           maxlength="100"/>
<#else>
    <input type="text" name="searchValue" maxlength="100"/>
</#if>
    <select name="searchType">
        <option id="searchByTitle" value="title">Title</option>
        <option id="searchByAuthor" value="author">Author</option>
        <option id="searchByISBN" value="isbn">ISBN</option>
    </select>
    <input type="submit" value="Go!" id="search"/>
</form>
<#if books??>
<ul class="book-list">
    <#list books as book>
        <li class="book">
                <img class="book-picture" src="${book.image}"/>
            <div class="book-content">
            <h3 class="book-title">${book
                .title}</h3>
                <div class="book-author">by ${book.author}</div>

            </div>
        </li>
    </#list>
</ul>

</#if>
</@layout.block>