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
    <#include "navbar.ftl">
</div>
<div class="container">
    <#nested>
</div>

</body>
</html>
</#macro>
