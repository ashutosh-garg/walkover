/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.Math.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class Validate extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String name=request.getParameter("name");
            String contact=request.getParameter("contact");
            String mailid=request.getParameter("mailid");
            String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            Boolean b = mailid.matches(EMAIL_REGEX);
            long contact1=Long.parseLong(contact);
            int nDigits =(int) (Math.floor(Math.log10(Math.abs(contact1))) + 1);
            long firstDigit=contact1/10;
            if(b&&nDigits==10&&(firstDigit==9||firstDigit==8||firstDigit==7)){
                 Class.forName("org.postgresql.Driver");
                 Connection c = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/template1","postgres", "postgres");
                 String sql="INSERT INTO USER1 (NAME, CONTACT, EMAILID) VALUES (?,?,?)";
                 PreparedStatement ps=(PreparedStatement) c.prepareStatement(sql);
                 ps.setString(1, name);
                 ps.setString(2, contact);
                 ps.setString(3, mailid);
                 ps.executeUpdate();
                 ps.close();
                 c.commit();
                 c.close();
                 out.println("inserted succesfully");
           }
           else{
               if(b==false){
                   out.println("enter valid emailid"+"\n");
               }
               if(nDigits!=10||firstDigit!=9||firstDigit!=8||firstDigit!=7){
                   out.println("contact number should be 10 digits and should start with 7, 8 or 9");
               }
            }
        }
        catch(Exception e){
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
