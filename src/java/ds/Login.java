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
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            UserBean userBean=(UserBean)request.getAttribute("userBean");
            Connection c=connect.Local.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement=c.prepareStatement("select * from authenticate where username=? and password=?");
            preparedStatement.setString(1, userBean.getUsername());
            preparedStatement.setString(2, userBean.getPassword());
            ResultSet resultSet=preparedStatement.executeQuery();
            boolean b=resultSet.next();
            resultSet.close();
            preparedStatement.close();
            c.close();
            if(b)
            {
                HttpSession session=request.getSession();
                session.setAttribute("userBean", userBean);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/HomePage.jsp");
                requestDispatcher.forward(request, response);
            }
            else
            {
                ErrorBean errorBean=new ErrorBean();
                errorBean.setErrorMessage("Invalid Username/Password");
                request.setAttribute("errorBean", errorBean);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch(Exception e)
        {
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
