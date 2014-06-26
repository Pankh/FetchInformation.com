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


public class Ship extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection c=connect.Local.getConnection();
        PreparedStatement p1=null;
        PreparedStatement p2,p3=null;
        ResultSet r1=null;
        int e=0;
        int od=0;
        String sku="";
        String d="";
        int qty=0;
        String ss="";
        int u=0;
        int del=0;
        String []a=request.getParameterValues("cbo");
        try {
            p2=c.prepareStatement("insert into inshipping(order_id,SKU,description,qty,shipping_speed) values(?,?,?,?,?)");
            while(e<a.length)
            {
            p1=c.prepareStatement("select * from ship where order_id=?");
            p1.setString(1, a[e]);
            r1=p1.executeQuery();
            while(r1.next())
            {
            od=r1.getInt(1);
            sku=r1.getString(2);
            d=r1.getString(3);
            qty=r1.getInt(4);
            ss=r1.getString(5);
            p2.setInt(1, od);
            p2.setString(2, sku);
            p2.setString(3, d);
            p2.setInt(4, qty);
            p2.setString(5, ss);
            u=p2.executeUpdate();
            }
            p3=c.prepareStatement("delete from ship where order_id=?");
            p3.setInt(1, od);
            del=p3.executeUpdate();
            e++;
            }
            r1.close();
            p1.close();
            p2.close();
            p3.close();
            c.close();
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/orders/open.jsp");
            requestDispatcher.forward(request, response);
        } catch(Exception ex)
        {
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/orders/open.jsp");
            requestDispatcher.forward(request, response);
            //out.println(ex);
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
