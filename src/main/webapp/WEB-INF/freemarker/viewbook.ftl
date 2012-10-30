<!DOCTYPE html>
<html>
<head>
<#if book??>
    <title>ReaderFeeder - ${book.title}</title>
<#else>
    <title>ReaderFeeder</title>
</#if>
</head>

<body>
<#if book??>
<img src=${book.image} width=100 height=50/>
<h1>${book.title}</a>
    <br>${book.author}
    <br>${book.description}
    <br>${book.ISBN10}
    <br>${book.ISBN13}
<#else>
    <p><a href="?booktitle=The Casual Vacancy">View The Casual Vacancy</a></p>
</#if>
</h1>

</body>
</html>