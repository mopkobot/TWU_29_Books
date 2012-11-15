<!DOCTYPE html>

<@layout.block>


    <div class="bookshelf">
        <div>My Bookshelf</div>


    <#if books?has_content>
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
                <form action="viewbook" method="GET">
                    <input type="submit" name="view-book-btn" value="View Book" class="view-book-btn"/>
                    <input type = "hidden" name="bookId" value="${book.id}">
                </form>
            </li>
        </#list>
    </ul>
    <#else>
        <div class="welcome">
            <#if bookNotFound??> ${bookNotFound} </#if>
        </div>
    </#if>

    </div>

</@layout.block>