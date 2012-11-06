<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
</head>

<body>
<form action="search_book" method="post">
<#if searchValue??>
    <input type="text" name="searchValue" value="${searchValue}" maxlength="100"/>
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
    <div class="book-info"/>
</#if>
</body>
</html>