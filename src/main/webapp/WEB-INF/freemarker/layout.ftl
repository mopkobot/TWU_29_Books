<!DOCTYPE html>
<#macro block title="ReaderFeeder">
<html>
<head>
    <title>
    ${title}
    </title>
    <link rel="stylesheet" type="text/css" href="static/css/common.css"/>
    <script type="text/javascript" language="javascript" src="static/javascript/lib/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" language="javascript" src="static/javascript/addBookToWantToReadList.js"></script>
</head>
<body>
<div class="navbar-fixed-top">
    <div class="logo"><a href="/twu"><img src="static/images/logo.gif"
                                          alt="Reader Feeder logo">
    </a></div>
    <div class="navbar-right">
        <span class="navbar-username">
            <#if Session.user??>
                Hello, <a href="/twu">${Session.user.name?cap_first}</a>!
            </#if>
        </span>

        <form action="search_book" class="navbar-search pull-left" method="post">
            <#if searchValue??>
                <input type="text" class="search-query" name="searchValue"
                       value="${searchValue?js_string?j_string?html}"/>
            <#else>
                <input type="text" class="search-query"
                       name="searchValue" placeholder="Search for a book"
                       maxlength="100"/>
            </#if>
            <select class="navbar dropdown-menu" name="searchType">
                <option id="searchByTitle" value="title"
                        <#if searchType?? && searchType  =
                        "title">selected</#if>>Title
                </option>
                <option id="searchByAuthor" value="author"
                        <#if searchType?? && searchType = "author">selected</#if>>
                    Author
                </option>
                <option id="searchByISBN" value="isbn"
                        <#if searchType?? && searchType = "isbn">selected</#if>>ISBN
                </option>
            </select>
            <input type="submit" value="Search" id="search"/>
        </form>
    </div>
</div>
<div class="container">
    <#nested>
</div>

</body>
</html>
</#macro>
