<#if book??>
    <#assign title="ReaderFeeder - "+ book.title>
</#if>

<@layout.block title=title  >
    <#if book??>
        <form action="recommend" method="POST">
            <input type="hidden" name="bookId" value="${book.id}"/>
            <input type="submit" value="Recommend"/>
        </form>
        <p>${book.recommendCount}</p>
        <div class="book-head">
            <div class="book-cover">
                <img class="book-img" src="${book.image}" alt="Picture not available"/>
            </div>
            <div class="book-info">
                <h1 class="title">${book.title}</h1>
                <h2 class="author">by ${book.author}</h2>
                <div class="isbn">
                    <#if book.ISBN10??>
                        ISBN-10:${book.ISBN10}
                    </#if>
                    <br>
                    <#if book.ISBN13??>
                        ISBN-13:${book.ISBN13}
                    </#if>
                </div>
            </div>
            <div class="book-actions">
                <button class="btn">Want to read</button>
            </div>
        </div>

        <section class="description" >
            <blockquote> ${book.description}  </blockquote>
        </section>
    <#else>
        <p><h1 class="title">${bookNotFound}</h1></p>
    </#if>

</@layout.block>