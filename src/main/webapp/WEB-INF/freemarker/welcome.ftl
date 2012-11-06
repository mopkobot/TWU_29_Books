<!DOCTYPE html>
<html>
<head>
    <title>ReaderFeeder</title>
    <link rel="stylesheet" type="text/css" href="static/css/common.css"/>
</head>

<body>
<div class="container">
    <div id="logo">
        <div>Reader Feeder</div>
        <div>My Bookshelf</div>
    </div>
<#if user??>
    <div class="welcome">
        Hello<h1 class="username">${user.name}</h1>
    </div>
<#else>
    <h1>Sorry!</h1>
</#if>
</div>
</body>
</html>
