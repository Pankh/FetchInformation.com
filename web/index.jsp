<%-- 
    Document   : index
    Created on : Jan 7, 2013, 4:00:29 PM
    Author     : pankh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AMAZING-Pro</title>
        <script src="/FetchInformation.com/index.js" language="JavaScript"></script>
    </head>
    <body>
        <jsp:useBean id='userBean' scope='request' class='ds.UserBean'/>
        <jsp:useBean id='errorBean' scope='request' class='ds.ErrorBean'/>
        <center>
        <div style="width:500px;height:210px;border:2px solid #333399;">
            <h1 style="color:#333399">Authentication</h1>
            <div style="color:#333399">
        <jsp:getProperty name='errorBean' property="errorMessage"/>
            </div>
        <form action="/FetchInformation.com/Login.jsp" method="POST" onsubmit="return checkInput(this)">
            <table border="0">
                <tbody>
                    <tr>
                        <td style="color:#333399">Username</td>
                        <td><input type="text" name="username" value="<%=userBean.getUsername()%>" />
                        <span id="usernameErrorSection"></span>
                 </tr>
                    <tr>
                        <td style="color:#333399">Password</td>
                        <td><input type="password" name="password" value="" />
                        <span id="passwordErrorSection"></span>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="2" align="center"><input type="submit" value="Login" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
        </div>
        </center>
    </body>
</html>
