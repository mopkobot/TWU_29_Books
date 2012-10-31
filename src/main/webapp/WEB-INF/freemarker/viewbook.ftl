<#if book??>
    <#assign title="ReaderFeeder - "+ book.title>
</#if>

<@layout.block title=title  >

    <#if book??>
        <div class="book-head">
            <div class="book-cover">
                <img class="book-img" src="${book.image}" alt="Picture not available"/>
            </div>
            <div class="book-info">
                <h1 class="title">${book.title}</h1>
                <h2 class="author">${book.author}</h2>
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
        </div>

        <div class="description">
            <hr>
        ${book.description}
        </div>
    <#else>
        <p><h1 class="title">${bookNotFound}</h1></p>
    </#if>

</@layout.block>