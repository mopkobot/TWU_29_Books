<!DOCTYPE html>
<#macro block title="ReaderFeeder">
    <html>
        <head>
            <title>
                ${title}
            </title>
            <link rel="stylesheet" type="text/css" href="static/css/common.css"/>
        </head>

        <body>
            <header class="header">

            </header>
            <div class="container">
                <#nested>
            </div>

            <footer class="footer">

            </footer>
        </body>
    </html>
</#macro>
