<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Admin Login</h1>
    <form action="/adminlogin" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Login</button>
    </form>

    <!-- Display error message if login fails -->
    <p style="color:red;">
        ${error}
    </p>
</body>
</html>
