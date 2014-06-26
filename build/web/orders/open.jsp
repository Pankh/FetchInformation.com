<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	<link rel="stylesheet" href="/FetchInformation.com/stylesheets.css" type="text/css" media="all">
    <link rel="stylesheet" href="/FetchInformation.com/menutabs.css" type="text/css" media="all">
    <link rel="stylesheet" href="/FetchInformation.com/tableStyleSheet.css" type="text/css" media="all">
	<meta http-equiv="Content-Language" content="en-us">
        <script language='JavaScript'>
c = false;
function checkedAll () {
if (c == false)
{
    c = true
}
else
{
    c = false
}
for (var i = 0; i < document.getElementById('myform').elements.length; i++) {
document.getElementById('myform').elements[i].checked = c;
}
}

function uncheck() {
if(c==true)
{
    c=false;
    document.getElementById('myform').elements[0].checked=false;
}
}
</script>
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
	<li id="current"><a href="/FetchInformation.com/orders/open.jsp"><span>OPEN</span></a></li>
	<li><a href="/FetchInformation.com/orders/inshipping.jsp"><span>IN-SHIPPING</span></a></li>
    <li><a href="/FetchInformation.com/orders/shipped.jsp"><span>SHIPPED</span></a></li>
    <li><a href="/FetchInformation.com/orders/stamps.jsp"><span>STAMPS.COM</span></a></li>
	<div id="row">
	</div>
	</ul>
	</div>
	</div>
    </td></tr>
    <tr><td>
         <%Connection c1=connect.Local.getConnection();
                      PreparedStatement p3,p4;
                      p3=c1.prepareStatement("select sku from ship group by SKU");
                      p4=c1.prepareStatement("select shipping_speed from ship group by shipping_speed");
                      ResultSet r=p3.executeQuery();
                      ResultSet rs=p4.executeQuery();
                    %>
         <form name="orders" action="/FetchInformation.com/Sort" method="GET">
         <table width="100%" border="0">
         <tr valign='middle'>
            <td align="center"><font class='topnavtextBlack'>SKU</font></td>
            <td align="center"><font class='topnavtextBlack'>Shipping</font></td>
            <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="center">
                    <select name="ITEMSKU[]" size="5" multiple="multiple" style="width:150px;">
                        <option value="" selected></option>
                        <%while(r.next()){%>
                        <option><%=r.getString(1)%></option>
                        <%}
                            r.close();
                            p3.close();%>
                    </select>
                    
                </td>
                <td align="center">
                <select name="SHIPTYPE[]" size="4" multiple="multiple" style="width:120px;">
                <option value="" SELECTED></option>
                <%while(rs.next()){%>
                <option><%=rs.getString(1)%></option>
                <%}
                            rs.close();
                            p4.close();%>
                </select>
            </td>
            <td width='5%' align='right'>
                <input type="image" src="/FetchInformation.com/images/blue.jpg" border="0" alt="Go" title=" Go ">
            </td>
            </tr>
       </table>
    </form>
    </td></tr>
     <tr>
				<td align='left'>
                    <a href="/FetchInformation.com/Import" ><img src="/FetchInformation.com/images/Update.png" border="0" alt="Import" title=" Import " width="64" height="64"></a></td>
	            </tr>

<tr><td>
            <%@page import="java.sql.*" %>
            <%PreparedStatement p1;
            p1=c1.prepareStatement("select * from ship");
            ResultSet r1;
            r1=p1.executeQuery();
            PreparedStatement p2;
            p2=c1.prepareStatement("select count(*) from ship");
            ResultSet r2;
            r2=p2.executeQuery();
            r2.next();
            int count=r2.getInt(1);
            %>
            <div class="tabCaptionCell0">
                <form id="myform" method="GET" name="myform" action="/FetchInformation.com/Ship" >
            <tr><td align="right"><%out.println("Orders To Be Fulfilled are "+count);%></td></tr>
            <table border="0" width="770" cellspacing="1" cellpadding="1">
                    
                    <tr class="tabCaptionCellO">
                        <th><input type="checkbox" value="ON" title="Select All" onclick="checkedAll();"/></th>
                        <th>OrderID</th>
                        <th>SKU</th>
                        <th>Description</th>
                        <th>Qty</th>
                        <th>Shipping Speed</th>
                    </tr>
                    <tbody>
                        <%int n=0;
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
                        <td align="center"><input type="checkbox" id="checkbox" name="cbo" value="<%=r1.getInt(1)%>"  title="Select to Ship" onclick="uncheck();"/></td>
                        <td><%=r1.getInt(1)%></td>
                        <td><%=r1.getString(2)%></td>
                        <td><%=r1.getString(3)%></td>
                        <td><%=r1.getInt(4)%></td>
                        <td><%=r1.getString(5)%></td>
                    </tr>
                    </tr>
                    <%}
                        r1.close();
                        p1.close();
                        c1.close();%>
                    </tbody>
                    <table width="770">
                        <td align="right">
                            <br>
                    <input type="image" src="/FetchInformation.com/images/Arrow_Right.png" border="0" alt="Move to Shipping" title=" Move to Shipping " width="64" height="64">
                        </td>
                       </table>
                    
            </table>
            </form>
            </div>
            </td></tr>
    </table>
</html>
