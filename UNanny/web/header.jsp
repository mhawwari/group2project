<%@page import="Servlet.ServletCommands"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--top ul start-->
<!--top ul start-->
 <form action="Controller" method="post">
<div id="rightTop">
      <% String UserName = (String)session.getAttribute(ServletCommands.attrUserName); %>
    <ul class="navi">
        <li><a href="#" class="#">Profile</a></li>
        <li><a href="#">Gallery</a></li>
        <li><a href="#">Topics</a></li>
        <li><a href="#">Messages</a></li>
    </ul>
    <p class="slog">
         <button type="submit" name="<%=ServletCommands.param_CNTRL_Logout%>">LogOut</button>
        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Welcome<span>&emsp;&emsp;
            <%if(UserName!=null){%>
                <%=UserName %>
                 <%}%></span>
    </p>
   
</div>
       </form>
<!--top ul end -->
</body>
</html>
