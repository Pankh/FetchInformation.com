 <%--
    Document   : Message
    Created on : Jan 7, 2013, 9:18:52 PM
    Author     : pankh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div align="center">
        <jsp:useBean id='messageBean' scope='request' class='ds.MessageBean'/>
        <h1 style="color:#333399">Notification</h1>
        <div style="color:#333399">
        <jsp:getProperty name='messageBean' property='message'/>
        </div>
        <br>
        <form action="/FetchInformation.com/index.jsp" method="POST">
            <input type="submit" value="Ok" />
        </form>
        </div>
    </body>
</html>
