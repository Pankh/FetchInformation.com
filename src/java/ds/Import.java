
package ds;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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


public class Import extends HttpServlet {
   
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection c1=connect.Amazon.getConnection();
        Connection c2=connect.Local.getConnection();
        try {
            PreparedStatement p1;
            p1=c1.prepareStatement("select orderid,SKU,description,qty,shipping_speed from orders,productid where orders.ASIN=productid.ASIN and shipping='N'");
            ResultSet r1;
            r1=p1.executeQuery();
            PreparedStatement p2;
            p2=c2.prepareStatement("insert into ship(order_id,SKU,description,qty,shipping_speed) values(?,?,?,?,?)");
            PreparedStatement p3;
            int n;
            int t;
            while(r1.next())
            {
                int oid=r1.getInt(1);
                String sku=r1.getString(2);
                String description=r1.getString(3);
                int qty=r1.getInt(4);
                String shipping=r1.getString(5);
                try
                {
                p2.setInt(1, oid);
                p2.setString(2,sku);
                p2.setString(3,description);
                p2.setInt(4,qty);
                p2.setString(5,shipping);
                n=p2.executeUpdate();
                p3=c1.prepareStatement("update orders set shipping='Y' where orderId=?");
                p3.setInt(1, oid);
                t=p3.executeUpdate();
                }catch(MySQLIntegrityConstraintViolationException v)
                {    
                }
            }
            p2.close();
            c2.close();
            r1.close();
            p1.close();
            c1.close();
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/orders/open.jsp");
            requestDispatcher.forward(request, response);
        } catch(Exception e)
        {
            out.println(e);
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
