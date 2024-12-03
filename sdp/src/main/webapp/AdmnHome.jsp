<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.jfsd.model.BlogPost" %>
<%@ page import="com.example.jfsd.model.Hashtag" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
</head>
<body>
    <h1>Welcome, ${username}!</h1>
    <h2>Welcome, ${user}</h2>

    <!-- Display blog posts -->
    <h2>Your Posts:</h2>

    <%
    @SuppressWarnings("unchecked")


        // Fetch the blogPosts list from the request attribute
        List<BlogPost> blogposts = (List<BlogPost>) request.getAttribute("blogposts");
        if (blogposts != null && !blogposts.isEmpty()) {
            for (BlogPost post : blogposts) {
    %>
                <div>
                    <h3><%= post.getTitle() %></h3>
                    <p><%= post.getDescription() %></p>

                    <!-- Display hashtags -->
                    <p><strong>Hashtags:</strong>
                        <%
                            if (post.getHashtags() != null && !post.getHashtags().isEmpty()) {
                                for (Hashtag hashtag : post.getHashtags()) {
                        %>
                                    #<%= hashtag.getText() %> 
                        <%
                                }
                            }
                        %>
                    </p>
                    

                    <% 
    String imagePath = post.getImagePath();
    if (imagePath != null && !imagePath.isEmpty()) {
        // Directly use the image path without the full path
        String fileName = imagePath;  // imagePath should already be just the filename
%>
    <img src="/images/<%= fileName %>" alt="<%= post.getTitle() %>" width="200">
<% 
    } else {
%>
    <img src="/static/images/default-image.jpg" alt="Default Post Image" width="200">
<% 
    }
%>

                </div>
                <hr>
    <%
            }
        } else {
    %>
        <p>No posts yet! Start by creating one.</p>
    <%
        }
    %>
</body>
</html>
