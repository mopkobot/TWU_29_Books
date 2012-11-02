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
            <div class="container">
                <#nested>
            </div>
        </body>
    </html>
</#macro>
