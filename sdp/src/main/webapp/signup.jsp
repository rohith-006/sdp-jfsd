<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
</head>
<body>
    <h1>Blogger Signup</h1>
    <form action="/createAccount" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Create Account</button>
    </form>

    <!-- Add the link for existing users -->
    <p>
        Already have an account? <a href="/login">Log in</a>
    </p>
</body>
</html>
