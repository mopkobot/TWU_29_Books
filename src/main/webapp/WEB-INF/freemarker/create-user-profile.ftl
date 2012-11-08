<!DOCTYPE html>
<html>
<head>
    <title>ReaderFeeder Create Profile</title>
    <link rel="stylesheet" type="text/css" href="static/css/common.css"/>
    <script src="static/js/jquery-1.8.2.min.js"></script>
</head>

<body>
<div class="profile">
<div id="logo">
<div>Reader Feeder</div>
<div>Create profile</div>
</div>
<div class="user-form">
<form method="POST" action="/twu/save">
    <pre style="font-size: 24px">Choose Your Username:  <input type="text" name="username"/></pre>
    <input type="submit" name="submit" value="Submit" class="submit-btn"/>
</form>
</div>
</div>
</body>
</html>
