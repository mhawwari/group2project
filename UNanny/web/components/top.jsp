<%@page import="Servlet.ServletCommands"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="css/ProjectStyle.css">
          <link rel="stylesheet" type="text/css" href="css/style.css">
        <title></title>
    </head>
    <body>
        <form id="TopViewComponent" action="Controller" method="POST">
        <div id="rightTop">
          <ul class="navi">
        <li><a href="#" class="#">Profile</a></li>
        <li><a href="#">Gallery</a></li>
        <li><a href="#">Topics</a></li>
        <li><a href="#">Messages</a></li>
    </ul>
    <p class="slog">
        <a class="bookmark" href="#">Log out</a>
        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Welcome<span>&emsp;&emsp;[username]</span></p>
        </div>
    </form>
    </body>
</html>
