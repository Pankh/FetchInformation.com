package ds;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Sort extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection c=connect.Local.getConnection();
        PreparedStatement p1=null,p2=null,p3=null;
        ResultSet r=null;
        int u=0;
        int del=0;
        int od=0;
        String sku="";
        String d="";
        int qty=0;
        String ss="";
        String item=request.getParameter("ITEMSKU[]");
        String ship=request.getParameter("SHIPTYPE[]");
        try {
            if(item!="" && ship!="")
            {
                p1=c.prepareStatement("select * from ship where sku=? and shipping_speed=?");
                p1.setString(1, item);
                p1.setString(2, ship);
                r=p1.executeQuery();
            }
            else if(item=="" && ship!="")
            {
                p1=c.prepareStatement("select * from ship where shipping_speed=?");
                p1.setString(1, ship);
                r=p1.executeQuery();
            }
            else if(item!="" && ship=="")
            {
                p1=c.prepareStatement("select * from ship where sku=?");
                p1.setString(1, item);
                r=p1.executeQuery();
            }
            else
            {
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/orders/open.jsp");
                requestDispatcher.forward(request, response);
            }
            p2=c.prepareStatement("insert into inshipping(order_id,SKU,description,qty,shipping_speed) values(?,?,?,?,?)");
            while(r.next())
            {
                od=r.getInt(1);
                sku=r.getString(2);
                d=r.getString(3);
                qty=r.getInt(4);
                ss=r.getString(5);
                p2.setInt(1, od);
                p2.setString(2, sku);
                p2.setString(3, d);
                p2.setInt(4, qty);
                p2.setString(5, ss);
                u=p2.executeUpdate();
                p3=c.prepareStatement("delete from ship where order_id=?");
                p3.setInt(1, od);
                del=p3.executeUpdate();
            }
            
            r.close();
            p1.close();
            p2.close();
            p3.close();
            c.close();
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/orders/open.jsp");
            requestDispatcher.forward(request, response);
        } catch(Exception e) {
            System.out.println(e);
        }
    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
