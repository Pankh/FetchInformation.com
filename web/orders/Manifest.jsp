<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	<link rel="stylesheet" href="/FetchInformation.com/stylesheets.css" type="text/css" media="all">
    <link rel="stylesheet" href="/FetchInformation.com/menutabs.css" type="text/css" media="all">
    <link rel="stylesheet" href="/FetchInformation.com/tableStyleSheet.css" type="text/css" media="all">
	<meta http-equiv="Content-Language" content="en-us">
        <title>AMAZING-Pro</title>
    </head>
<tr>
        <div align="center">
	<font class="topnavtextBlack">
	Pankhuri Goyal&nbsp; <A href="/FetchInformation.com/Logout"> Logout </A>
	</font>
    </div>
	</tr>
    <br>
    <br>

    <table border="0" width="770" id="table3" style="border-collapse: collapse" >


    <tr>
    <td width="770" valign='top'>
    <tr>
	<td width='100%'>
	<div id="maintab">
	<li><a href="/FetchInformation.com/HomePage.jsp"><span>HOME</span></a></li>
	<li id="current"><a href="/FetchInformation.com/orders/orders.jsp"><span>ORDERS</span></a></li>
	<li><a href="/FetchInformation.com/reports/reports.jsp"><span>REPORTS</span></a></li>
	<li><a href="/FetchInformation.com/SKU/sku.jsp"><span>SKU</span></a></li>
	<li><a href="/FetchInformation.com/admin/admin.jsp"><span>ADMIN</span></a></li>
	<li><a href="/FetchInformation.com/search/search.jsp"><span>SEARCH</span></a></li>
	<div id="row">
	</div>
    <div id="subtab">
	<ul>
	<li><a href="/FetchInformation.com/orders/open.jsp"><span>OPEN</span></a></li>
	<li id="current"><a href="/FetchInformation.com/orders/inshipping.jsp"><span>IN-SHIPPING</span></a></li>
    <li><a href="/FetchInformation.com/orders/shipped.jsp"><span>SHIPPED</span></a></li>
    <li><a href="/FetchInformation.com/orders/stamps.jsp"><span>STAMPS.COM</span></a></li>
	<div id="row">
	</div>
	</ul>
	</div>
	</div>
    </td></tr>

<tr><td>
    <table width="770" border="0">
    <tr valign="middle">
        <br>
        <td align="center"><a href="/FetchInformation.com/Manifest" ><img src="/FetchInformation.com/images/pick-list.png" border="0" alt="Pick-List" title=" Pick-List " width="64" height="64"></a></td>
        <td align="center"><a href="/FetchInformation.com/" ><img src="/FetchInformation.com/images/purchase.jpg" border="0" alt="Purchase Label" title=" Purchase Label " width="64" height="64"></a></td>
        <td align="center"><a href="/FetchInformation.com/" ><img src="/FetchInformation.com/images/print.jpg" border="0" alt="Print All Packing Slip-Label" title=" Print All Packing Slip-Label " width="64" height="64"></a></td>
        <td align="center"><a href="/FetchInformation.com/" ><img src="/FetchInformation.com/images/done.jpg" border="0" alt="Update As Shipped On Amazon" title=" Update As Shipped On Amazon " width="64" height="64"></a></td>
    </tr>
    </table>
</td></tr>

<tr><td>
    <br>
        <table border="0" width="770" cellpadding="10">
            <thead>
                <tr class="tabCaptionCellO">
                    <th>Description</th>
                    <th>Qty</th>
                </tr>
            </thead>
            <tbody>
                <%@page import="java.sql.*" %>
                <%  Connection c1=connect.Local.getConnection();
                    PreparedStatement p1;
                    p1=c1.prepareStatement("select description,sum(qty) from inshipping group by description");
                    ResultSet r1;
                    r1=p1.executeQuery();
                    int n=0;
                    while(r1.next()){
                        if(n==0){%>
                    <tr class="txnTextRowEven">
                        <%n=1;
                        }
                        else{
                        %>
                        <tr class="txnTextRowOdd">
                            <%n=0;
                        }%>
                        <td align="center"><%=r1.getString(1)%></td>
                        <td align="center"><%=r1.getInt(2)%></td>
                    </tr></tr>
                     <%}
                        r1.close();
                        p1.close();
                        c1.close();%>
            </tbody>
        </table>

</td></tr>
    </table>
