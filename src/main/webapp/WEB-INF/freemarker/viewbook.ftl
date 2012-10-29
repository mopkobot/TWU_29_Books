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
<#else>
    <p><a href="?booktitle=Harry Potter">View Harry Potter</a></p>
</#if>
</h1>

</body>
</html>