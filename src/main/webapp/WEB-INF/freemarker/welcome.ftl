<!DOCTYPE html>
<html>
<head>
    <title>ReaderFeeder</title>
</head>

<body>
<#if user??>
    <div>
        Hello<h1 class="username">${user.name}</h1>
    </div>
<#else>
    <h1>Sorry!</h1>
</#if>
</body>
</html>
