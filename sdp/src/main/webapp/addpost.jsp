<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Post</title>
</head>
<body>
    <h1>Add a New Post</h1>

    <!-- Debugging: Display the username -->
    <p>Logged in as: ${username}</p>

    <!-- Form for creating a post -->
    <form action="${pageContext.request.contextPath}/savePost" method="post" enctype="multipart/form-data">

        <!-- Hidden input to pass the username -->
        <input type="hidden" name="username" value="${username}" />

        <!-- Input for post title -->
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required>
        <br><br>

        <!-- Textarea for post description -->
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea>
        <br><br>

        <!-- Input for hashtags -->
        <label for="hashtags">Hashtags (comma-separated):</label>
        <input type="text" id="hashtags" name="hashtags">
        <br><br>

        <!-- Input for image file -->
        <label for="image">Upload Image:</label>
        <input type="file" id="image" name="image" accept="image/*">
        <br><br>

        <!-- Submit button -->
        <button type="submit">Add Post</button>
    </form>
</body>
</html>
