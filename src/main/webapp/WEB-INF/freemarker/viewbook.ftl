<#if book??>
    <#assign title="ReaderFeeder - "+ book.title>
</#if>

<@layout.block title=title>
<#if notification??>
    <div class="alert alert-success">${notification}</div>
</#if>
    <#if book??>
    <#if notification?has_content>
    <h3 class="alert alert-success">${notification}</h3>
    </#if>
        <div class="book-head">
            <div class="book-cover">
                <img class="book-img" src="${book.image}" alt="Picture not available"/>
            </div>
            <div class="book-info">
                <h1 class="title">${book.title}</h1>
                <div class="recommendations">
                    <form action="recommend" method="POST">
                        <input type="hidden" name="bookId" value="${book.id?c}"/>
                        <input class="recommend-btn" type="image" value="Recommend" src="static/images/rec_but.png" />
                        <div class="recommend-text">${book.recommendCount} Recommendation/s</div>
                    </form>
                </div>
                <#if book.author?has_content>
                <h2 class="author">by ${book.author}</h2>
                </#if>
                <h2 class="bookId" style="display: none;">${book.id}</h2>
                <div class="isbn">
                    <#if book.ISBN10?has_content>
                        ISBN-10:${book.ISBN10}
                    </#if>
                    <br>
                    <#if book.ISBN13?has_content    >
                        ISBN-13:${book.ISBN13}
                    </#if>
                </div>
            </div>
            <div class="book-actions">
                <button class="add-btn">Want to read</button>
            </div>
        </div>

        <section class="description" >
            <blockquote> ${book.description}  </blockquote>
        </section>
    <#else>
        <p><h1 class="title"><#if bookNotFound??>${bookNotFound}</#if></h1></p>
    </#if>

</@layout.block>