<!DOCTYPE html>
<html>

<head>
<#if book??>
    <title>ReaderFeeder - ${book.title}</title>
<#else>
    <title>ReaderFeeder</title>
</#if>
    <link rel="stylesheet" type="text/css" href="static/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
</head>


<body>
<#if book??>
<div class="container">
<div class="book-head">
    <div class="book-cover">
        <img src="${book.image}"/>
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
</div>

<#else>
    <p><a href="?booktitle=The Casual Vacancy">View The Casual Vacancy</a></p>
</#if>

</body>
</html>