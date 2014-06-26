<jsp:useBean id='userBean' scope='request' class='ds.UserBean'/>
<jsp:setProperty name='userBean' property='*'/>
<jsp:forward page='/Login'/>