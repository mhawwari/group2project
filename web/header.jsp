<jsp:directive.page import="Servlet.ServletCommands"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link href="../web/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--top ul start-->
    <div id="rightTop">
        <br/>
        <p class="slog">
            &emsp;&emsp;&emsp;&emsp;Welcome to UNanny!<span>[username]</span>
            <button type="submit" name="<%=ServletCommands.param_CNTRL_Logout%>">Log Out</button>
        </p>

    </div>
<!--top ul end -->
</body>
</html>